package com.cesecsh.baseframelibrary.net.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 上海中电
 * on 2017/1/3
 */

public class ListJson<T> implements Serializable {
    @SerializedName("objs")
    private List<T> objs;
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private String code;

    public List<T> getObjs() {
        return objs;
    }

    public void setObjs(List<T> objs) {
        this.objs = objs;
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
