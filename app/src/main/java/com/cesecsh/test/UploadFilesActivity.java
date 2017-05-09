package com.cesecsh.test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cesecsh.baseframelibrary.net.base.BaseApiService;
import com.cesecsh.baseframelibrary.permission.PermissionActivity;
import com.cesecsh.baseframelibrary.permission.PermissionListener;
import com.cesecsh.baseframework.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.iwf.photopicker.PhotoPicker;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UploadFilesActivity extends PermissionActivity {
    private List<String> selectPictures;
    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_files);
        selectPictures = new ArrayList<>();
        mContext = this;
    }

    public void selectPicture(View view) {
        requestPermission(new PermissionListener() {
            @Override
            public void onGranted() {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(true)
                        .setSelected(new ArrayList<>(selectPictures))
                        .start(mContext, PhotoPicker.REQUEST_CODE);
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {

            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PhotoPicker.REQUEST_CODE) {
                selectPictures = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (selectPictures != null) {
                    uploadImage2Web();
                }
            }
        }
    }

    private void uploadImage2Web() {
        HashMap<String, RequestBody> map = new HashMap<>();
        for (String selectPicture : selectPictures) {
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), selectPicture);
            String fileName = selectPicture.substring(selectPicture.lastIndexOf("/"), selectPicture.lastIndexOf("."));
            RequestBody tokenBody = RequestBody.create(
                    MediaType.parse("multipart/form-data"), fileName);
            map.put(fileName, requestFile);
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), selectPictures.get(0));
        Map<String, String> params = new HashMap<>();
        params.put("siteId", "402882ce552f085101554d5bb928059f");
        new UploadRetrofit().getICSAPiService("http://192.168.0.251:8888/ic/")
                .create(BaseApiService.class)
                .upLoadImage("attachmentController.app?doUploadFile", requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("8888888888888888888");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        byte bytes[] = new byte[1024];
                        try {
                            inputStream.read(bytes);
                            String string = new String(bytes);
                            System.out.println(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
//        new UploadRetrofit()
//                .getICSAPiService("http://115.238.41.78:8861/device/")
//                .create(BaseApiService.class)
//                .uploadFiles("attachmentController.app?doUploadFile", map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ResponseBody>() {
//                    @Override
//                    public void onCompleted() {
//                        System.out.println("8888888888888888888");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println(e.toString());
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody responseBody) {
//                        InputStream inputStream = responseBody.byteStream();
//                        byte bytes[] = new byte[1024];
//                        try {
//                            inputStream.read(bytes);
//                            String string = new String(bytes);
//                            System.out.println(string);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
    }

    public void submit(View view) {
//        userName=谢青仂&userId=402882ce55d765760155d8b161bd0020&latitude=30.3214&longitude=120.124168&time=2017-05-04 14:48:43
        Map<String, Object> map = new HashMap<>();
        String name = map.getClass().getName();
        map.put("userName", "xjxjxjxjxjxjxjxjxjxjxj");
        map.put("userId", "9898989898989898989898");
        map.put("latitude", "30.3214");
        map.put("longitude", "120.124168");
        map.put("time", "2017-05-04 14:48:43");
        new UploadRetrofit()
                .getICSAPiService("http://192.168.1.146:3000/")
                .create(BaseApiService.class)
                .executePostBody("map/api/upload_position", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("xql", e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.d("xql", responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}
