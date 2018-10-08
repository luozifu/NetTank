package com.miaoxing.nettank.net;


import io.reactivex.Observable;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 *
 * @Date : 2018/8/6
 * @Desc :
 */
public interface ApiService {

    @POST("user/login")
    Observable<Response> login(@Body Request request);
}
