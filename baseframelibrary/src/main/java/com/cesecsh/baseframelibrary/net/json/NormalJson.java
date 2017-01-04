package com.cesecsh.baseframelibrary.net.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 上海中电
 * on 2016/10/26
 */

public class NormalJson<T> {
    @SerializedName("obj")
    private T obj;
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private String code;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
