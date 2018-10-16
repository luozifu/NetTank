package com.miaoxing.nettank.net;


import io.reactivex.Observable;
import okhttp3.Response;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 *
 * @Date : 2018/8/6
 * @Desc :
 */
public interface ApiService {

    @POST("netlogin")
    Observable<Response> login(@Path("UserID")String userID,@Path("PWD")String pwd);
}
