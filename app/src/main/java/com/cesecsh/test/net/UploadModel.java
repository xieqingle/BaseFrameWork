package com.cesecsh.test.net;

import java.io.File;

/**
 * Created by RockQ on 2017/3/9
 */

public class UploadModel {
    private File file;

    public UploadModel(File file) {
        this.file = file;
    }

    public UploadModel() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
