package com.cesecsh.baseframelibrary.net.utils;

import android.support.annotation.Nullable;

import com.cesecsh.baseframelibrary.net.request.ProgressRequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by RockQ on 2017/3/22
 * 用于将文件转化成可以上传的文件工具类
 */

public class FileUploadUtils {
    /**
     * 不让使用者初始化
     */
    public FileUploadUtils() {
        throw new IllegalStateException("You can not fuck me");
    }

    /**
     * 根据文件路径转化成上传文件的multiPartBody格式
     *
     * @param filePaths 文件路径
     * @return 上传的multipartBody
     */
    public static MultipartBody path2MultipartBody(@Nullable List<String> filePaths) {
        if (filePaths == null || filePaths.isEmpty()) return null;
        RequestBody requestBody;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (!file.exists())
                continue;
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart(file.getName(), file.getName(), requestBody);
        }
        return builder.build();
    }

    /**
     * 根据文件路径转化成上传文件的multiPartBody格式，并且监听进度回调
     *
     * @param filePaths 文件路径
     * @param listener  进度回调接口
     * @return 上传的multipartBody
     */
    public static MultipartBody path2MultipartBody(@Nullable List<String> filePaths, ProgressRequestBody.Listener listener) {
        if (filePaths == null || filePaths.isEmpty()) return null;
        RequestBody requestBody;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (!file.exists())
                continue;
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, listener, file.getName());
            builder.addFormDataPart(file.getName(), file.getName(), progressRequestBody);
        }
        return builder.build();
    }

    /**
     * 根据文件转化成上传文件的multiPartBody格式
     *
     * @param files 文件
     * @return 上传的multipartBody
     */
    public static MultipartBody file2MultipartBody(@Nullable List<File> files) {
        if (files == null || files.isEmpty()) return null;
        RequestBody requestBody;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            if (!file.exists())
                continue;
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart(file.getName(), file.getName(), requestBody);
        }
        return builder.build();
    }

    /**
     * 根据文件转化成上传文件的multiPartBody格式，并且监听进度回调
     *
     * @param files    文件集合
     * @param listener 进度回调接口
     * @return 上传的multipartBody
     */
    public static MultipartBody file2MultipartBody(@Nullable List<File> files, ProgressRequestBody.Listener listener) {
        if (files == null || files.isEmpty()) return null;
        RequestBody requestBody;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            if (!file.exists())
                continue;
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, listener);
            builder.addFormDataPart(file.getName(), file.getName(), progressRequestBody);
        }
        return builder.build();
    }

    /**
     * 根据文件路径转化成上传文件的  List<MultipartBody.Part> 格式
     *
     * @param filePaths 文件路径
     * @return 上传的  List<MultipartBody.Part>
     */
    public static List<MultipartBody.Part> path2MultipartParts(@Nullable List<String> filePaths) {
        if (filePaths == null || filePaths.isEmpty()) return null;
        RequestBody requestBody;
        List<MultipartBody.Part> parts = new ArrayList<>(filePaths.size());
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (!file.exists())
                continue;
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(file.getName(), file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    /**
     * 根据文件路径转化成上传文件的  List<MultipartBody.Part> 格式，并且监听进度回调
     *
     * @param filePaths 文件路径
     * @param listener  进度回调接口
     * @return 上传的  List<MultipartBody.Part>
     */
    public static List<MultipartBody.Part> path2MultipartParts(@Nullable List<String> filePaths, ProgressRequestBody.Listener listener) {
        if (filePaths == null || filePaths.isEmpty()) return null;
        RequestBody requestBody;
        List<MultipartBody.Part> parts = new ArrayList<>(filePaths.size());
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (!file.exists())
                continue;
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, listener);
            MultipartBody.Part part = MultipartBody.Part.createFormData(file.getName(), file.getName(), progressRequestBody);
            parts.add(part);
        }
        return parts;
    }

    /**
     * 根据文件转化成上传文件的  List<MultipartBody.Part> 格式
     *
     * @param files 文件
     * @return 上传的  List<MultipartBody.Part>
     */
    public static List<MultipartBody.Part> file2MultipartParts(@Nullable List<File> files) {
        if (files == null || files.isEmpty()) return null;
        RequestBody requestBody;
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            if (!file.exists())
                continue;
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(file.getName(), file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    /**
     * 根据文件转化成上传文件的 List<MultipartBody.Part>格式，并且监听进度回调
     *
     * @param files    文件集合
     * @param listener 进度回调接口
     * @return 上传的  List<MultipartBody.Part>
     */
    public static List<MultipartBody.Part> file2MultipartParts(@Nullable List<File> files, ProgressRequestBody.Listener listener) {
        if (files == null || files.isEmpty()) return null;
        RequestBody requestBody;
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            if (!file.exists())
                continue;
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, listener);
            MultipartBody.Part part = MultipartBody.Part.createFormData(file.getName(), file.getName(), progressRequestBody);
            parts.add(part);
        }
        return parts;
    }


}
