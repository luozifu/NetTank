package com.miaoxing.nettank.net;


import com.miaoxing.nettank.ui.login.LoginResponse;
import com.miaoxing.nettank.ui.main.MainResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Date : 2018/8/6
 * @Desc :
 */
public interface ApiService {
    /**
     * 登录
     * @param userID
     * @param pwd
     * @return
     */
    @POST("Api/netlogin")
    @FormUrlEncoded
    Observable<Result<LoginResponse>> login(@Field("UserID") String userID, @Field("PWD") String pwd);

    /**
     * 首页
     * @param userID
     * @return
     */
    @POST("Api/stat")
    @FormUrlEncoded
    Observable<Result<MainResponse>> getMain(@Field("UserID") String userID);

    /**
     * 获取油站详情
     * @param stationID
     * @return
     */
    @POST("Api/station_info")
    @FormUrlEncoded
    Observable getStationInfo(@Field("StationID") String stationID);

    /**
     * 获取油站下的所有油罐
     * @param stationID
     * @return
     */
    @POST("Api/tank_list")
    @FormUrlEncoded
    Observable getTankList(@Field("StationID") String stationID);

    /**
     * 获取进油记录
     * @param stationID
     * @param startTime
     * @param endTime
     * @param page
     * @param tankID
     * @return
     */
    @POST("Api/record_list")
    @FormUrlEncoded
    Observable getRecordList(@Field("StationID") String stationID,
                             @Field("start_time") String startTime,
                             @Field("end_time") String endTime,
                             @Field("page") int page,
                             @Field("TankID") int tankID);

    /**
     * 获取报警记录
     * @param stationID
     * @param startTime
     * @param endTime
     * @param page
     * @param tankID
     * @return
     */
    @POST("Api/tankalarm")
    @FormUrlEncoded
    Observable getAlarmList(@Field("StationID") String stationID,
                             @Field("start_time") String startTime,
                             @Field("end_time") String endTime,
                             @Field("page") int page,
                             @Field("TankID") int tankID);

    /**
     * 报警推送
     * @param userID
     * @return
     */
    @POST("Api/tankinfoalarm")
    @FormUrlEncoded
    Observable pullAlarm(@Field("UserID") String userID);
}
