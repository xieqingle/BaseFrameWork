package com.cesecsh.baseframelibrary.net.base;

import com.cesecsh.baseframelibrary.net.json.ListJson;
import com.cesecsh.baseframelibrary.net.json.NormalJson;
import com.cesecsh.baseframelibrary.net.json.PageListInfo;
import com.cesecsh.baseframelibrary.net.json.PageListJson;

import okhttp3.RequestBody;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 上海中电
 * on 2017/1/5
 */

public interface CommonApiService {
    @POST("{url}")
    Observable<RequestBody> executePost(@Path("url") String url, @Query("params") String params);

    @POST("{url}")
     Observable<NormalJson> executePostNormal(@Path("url") String url, @Query("params") String params);

    @POST("{url}")
    <T> Observable<ListJson<T>> executePostList(@Path("url") String url, @Query("params") String params, T t);

    @POST("{url}")
    <T> Observable<PageListJson<PageListInfo<T>>> executePageList(@Path("url") String url, @Query("params") String params, T t);
}
