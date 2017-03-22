package com.cesecsh.test;

import java.io.Serializable;

/**
 * Created by 上海中电
 * on 2016/11/8
 * 媒体文件
 */

public class Media implements Serializable {
    private String location;
    private String id;
    private int type;
    private String path;
    private String extend;
    private String title;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
