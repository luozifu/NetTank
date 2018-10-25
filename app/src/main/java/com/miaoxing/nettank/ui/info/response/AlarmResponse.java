package com.miaoxing.nettank.ui.info.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author : Luozifu
 * @date : 2018/10/20
 */
@Entity
public class AlarmResponse implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("StationName")
    public String stationName;

    @SerializedName("TankName")
    public String tankName;

    @SerializedName("FuelName")
    public String fuelName;

    @SerializedName(value = "AlarmTime",alternate = "UpdateTime")
    public String alarmTime;

    @SerializedName("AlarmInfo")
    public int alarmInfo;
}
