package com.cesecsh.test.net;

/**
 * Created by RockQ on 2017/3/9
 */

public interface IProgress {
    void onProgress(long progress, long total, int dex, boolean done);

    void onSuccess(String result);

    void onFailed(Throwable e, String reason);

}
