package com.miaoxing.nettank.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.net.ApiClient;
import com.miaoxing.nettank.net.Result;
import com.miaoxing.nettank.ui.info.response.AlarmResponse;
import com.miaoxing.nettank.ui.main.MainActivity;
import com.miaoxing.nettank.util.DateTimeUtils;
import com.miaoxing.nettank.util.SPUtils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : Administrator
 * @Date : 2018/10/25 0025
 */
public class PullService extends Service {

    public static final String ACTION = "com.miaoxing.nettank.service.PullService";

    private NotificationCompat.Builder builder;

    private NotificationManager manager;

    private String userID;
    private RemoteViews remoteViews;

    private int count = 1;

    private Map<Integer, String> mMap;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        userID = (String) SPUtils.get(this,Constant.PREFERENCES_USER_KEY,"");
        mMap = new HashMap<>();
        mMap.put(Constant.ALARM_WATER_FLOAT, getResources().getString(R.string.alarm_water_float));
        mMap.put(Constant.ALARM_ALPENSTOCK, getResources().getString(R.string.alarm_probe));
        mMap.put(Constant.ALARM_LOW_TMP, getResources().getString(R.string.alarm_low_tmp));
        mMap.put(Constant.ALARM_HIGH_TMP, getResources().getString(R.string.alarm_high_tmp));
        mMap.put(Constant.ALARM_WATER_LOW, getResources().getString(R.string.alarm_water_low));
        mMap.put(Constant.ALARM_WATER_HIGH, getResources().getString(R.string.alarm_water_high));
        mMap.put(Constant.ALARM_FUEL_LOW, getResources().getString(R.string.alarm_fuel_low));
        mMap.put(Constant.ALARM_FUEL_HIGH, getResources().getString(R.string.alarm_fuel_high));
        initManager();
    }

    private void initManager() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this,getPackageName());
        remoteViews = new RemoteViews(getPackageName(), R.layout.notify);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getPackageName(), getPackageName(), importance);
            manager.createNotificationChannel(channel);
        }
        //创建一个pendingIntent对象用于点击notification之后跳转
        PendingIntent intent = PendingIntent.getActivity(this,0,
                new Intent(this,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(intent);
        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        builder.setSmallIcon(R.drawable.vector_drawable_logo);
        builder.setAutoCancel(true);
        //builder.setGroup(getPackageName());
        builder.setDefaults(Notification.DEFAULT_SOUND);
    }

    private void showNotifiCation(AlarmResponse alarm) {
        long alarmTime = 0;
        try {
           alarmTime  = DateTimeUtils.getTime(alarm.alarmTime,DateTimeUtils.DF_YYYY_MM_DD_HH_MM_SS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long dValue = System.currentTimeMillis() - alarmTime;
        //如果超过一分钟不执行
        if(dValue > 60*1000){
            return;
        }
        remoteViews.setTextViewText(R.id.tv_station_notify,alarm.stationName);
        remoteViews.setTextViewText(R.id.tv_tank_notify,alarm.tankName);
        String alarmInfo = mMap.get(alarm.alarmInfo);
        if(TextUtils.isEmpty(alarmInfo)){
            alarmInfo = "无效报警";
        }
        remoteViews.setTextViewText(R.id.tv_alarm_notify,alarmInfo);
        remoteViews.setTextViewText(R.id.tv_alarm_time_notify,alarm.alarmTime);
        builder.setCustomContentView(remoteViews);
        manager.notify(++count,builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ApiClient.getService()
                .pullAlarm(userID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<AlarmResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<AlarmResponse>> listResult) {
                        Log.e("pull","1");
                        if(listResult.getCode() != Constant.CODE_SUCCESS){
                            return;
                        }
                        List<AlarmResponse> alarmList = listResult.getData();
                        for(AlarmResponse alarm :alarmList){
                            showNotifiCation(alarm);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return super.onStartCommand(intent, flags, startId);
    }

    /*Intent i = new Intent(this, MainActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,
            PendingIntent.FLAG_ONE_SHOT);*/

}
