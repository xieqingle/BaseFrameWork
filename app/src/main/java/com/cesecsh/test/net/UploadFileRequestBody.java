package com.cesecsh.test.net;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by RockQ on 2017/3/8
 */

public class UploadFileRequestBody extends RequestBody {
    private RequestBody requestBody;
    private UploadBodyListener mUploadBodyListener;
    private BufferedSink bufferedSink;

    public UploadFileRequestBody(File file, UploadBodyListener mUploadBodyListener) {
        this.requestBody = RequestBody.create(MediaType.parse("multipart/from-data"), file);
        this.mUploadBodyListener = mUploadBodyListener;
    }

    public UploadFileRequestBody(RequestBody requestBody, UploadBodyListener mUploadBodyListener) {
        this.requestBody = requestBody;
        this.mUploadBodyListener = mUploadBodyListener;
    }


    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null)
            bufferedSink = Okio.buffer(sink(sink));
        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private Sink sink(BufferedSink sink) {
        return new ForwardingSink(sink) {
            long byteWritten = 0L;
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    contentLength = contentLength();
                }
                byteWritten += byteCount;
                mUploadBodyListener.onProgress(byteWritten, contentLength, byteWritten >= contentLength);
            }
        };
    }

    public interface UploadBodyListener {
        void onProgress(long progress, long total, boolean done);
    }
}
