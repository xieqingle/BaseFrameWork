package com.cesecsh.baseframelibrary.net.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 上海中电
 * on 2017/1/3
 */

public class SimpleJson {
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private String code;

    public String getCode() {
        return code;
    }

    public SimpleJson setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {

        return message;
    }

    public SimpleJson setMessage(String message) {
        this.message = message;
        return this;
    }
}
