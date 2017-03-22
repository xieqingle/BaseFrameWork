package com.cesecsh.test.net;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RockQ on 2017/3/8
 */

public class FileUploadEntity {
    private String url;
    private long size;
    private List<File> files = new ArrayList<>();

    public FileUploadEntity(String url, List<File> files) {
        this.url = url;
        this.files = files;
        initSize();
    }

    private void initSize() {
        if (files != null && files.size() > 0) {
            for (File file : files) {
                this.size += file.length();
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
