package com.cesecsh.baseframelibrary.net.upload;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by RockQ on 2017/3/17
 */

public class ProgressResponseBody extends ResponseBody {
    private ResponseBody mResponseBody;

    private DownloadProgressListener listener;

    private BufferedSource mBufferedSource;

    public ProgressResponseBody(ResponseBody mResponseBody, DownloadProgressListener listener) {
        this.mResponseBody = mResponseBody;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (null == mBufferedSource) {
            mBufferedSource = Okio.buffer(source(mResponseBody.source()));
        }
        return mBufferedSource;
    }

    private Source source(BufferedSource source) {
        return new ForwardingSource(source) {
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long totalBytesRead = 0L;
                long byteRead = super.read(sink, byteCount);
                totalBytesRead += byteRead != -1 ? byteRead : 0;
                if (listener != null) {
                    listener.onProgress(totalBytesRead, mResponseBody.contentLength());
                }
                return byteRead;
            }
        };
    }

    public interface DownloadProgressListener {
        void onProgress(long progress, long contentLength);
    }
}
