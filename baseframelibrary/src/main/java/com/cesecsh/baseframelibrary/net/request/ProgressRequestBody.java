package com.cesecsh.baseframelibrary.net.request;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by 上海中电
 * on 2017/1/3
 * 自定义RequestBody 拦截进度，添加监听接口，返回进度
 */
public class ProgressRequestBody extends RequestBody {

    protected RequestBody delegate;
    protected Listener listener;
    protected String name;
    private static final String TAG = "ProgressRequestBody";

    protected CountingSink countingSink;

    public ProgressRequestBody(RequestBody delegate, Listener listener, String name) {
        this.delegate = delegate;
        this.listener = listener;
        this.name = name;
    }

    public ProgressRequestBody(RequestBody delegate, Listener listener) {
        this.delegate = delegate;
        this.listener = listener;
        this.name = TAG;
    }

    @Override
    public MediaType contentType() {
        return delegate.contentType();
    }

    @Override
    public long contentLength() {
        try {
            return delegate.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

        countingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(countingSink);

        delegate.writeTo(bufferedSink);

        bufferedSink.flush();
    }

    protected final class CountingSink extends ForwardingSink {

        private long bytesWritten = 0;

        public CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);

            bytesWritten += byteCount;
            listener.onRequestProgress(bytesWritten, contentLength(), name);
        }

    }

    public interface Listener {
        void onRequestProgress(long bytesWritten, long contentLength, String name);
    }

}