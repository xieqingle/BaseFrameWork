package com.cesecsh.baseframelibrary.net.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 上海中电
 * on 2016/12/29
 */

public class PageListJson<T> {
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private String code;
    @SerializedName("obj")
    private PageListInfo<T> pageListInfo;

    public String getMessage() {
        return message;
    }

    public PageListJson setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCode() {
        return code;
    }

    public PageListJson setCode(String code) {
        this.code = code;
        return this;
    }

    public PageListInfo<T> getPageListInfo() {
        return pageListInfo;
    }

    public PageListJson setPageListInfo(PageListInfo<T> pageListInfo) {
        this.pageListInfo = pageListInfo;
        return this;
    }


}
