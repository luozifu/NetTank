package com.miaoxing.nettank.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.net.ApiClient;
import com.miaoxing.nettank.net.Result;
import com.miaoxing.nettank.ui.info.response.AlarmResponse;
import com.miaoxing.nettank.util.DateTimeUtils;
import com.miaoxing.nettank.util.SPUtils;

import java.text.ParseException;
import java.util.List;

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        userID = (String) SPUtils.get(this,Constant.PREFERENCES_USER_KEY,"");
        initManager();
    }

    private void initManager() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this,getPackageName());
        remoteViews = new RemoteViews(getPackageName(), R.layout.notify);
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
        remoteViews.setTextViewText(R.id.tv_alarm_notify,alarm.alarmInfo+"");
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
