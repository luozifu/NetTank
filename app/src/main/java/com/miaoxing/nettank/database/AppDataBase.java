package com.miaoxing.nettank.database;

import android.content.Context;

import com.miaoxing.nettank.ui.info.response.AlarmDao;
import com.miaoxing.nettank.ui.info.response.AlarmResponse;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 *
 * @Date : 2018/10/06
 * @Desc : 应用主数据库
 */
@Database(entities = AlarmResponse.class, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static final String DB_NAME = "data.db";

    private static volatile AppDataBase mDataBase;

    public static AppDataBase getInstance(Context context) {
        if (mDataBase == null) {
            mDataBase = Room.databaseBuilder(context, AppDataBase.class, DB_NAME).build();
        }
        return mDataBase;
    }

    public abstract AlarmDao getUserDao();

}
