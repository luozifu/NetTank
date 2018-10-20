package com.miaoxing.nettank.ui.info.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author : Luozifu
 * @date : 2018/10/20
 */

public class AlarmResponse implements Serializable {
    @SerializedName("TankName")
    public String tankName;

    @SerializedName("FuelName")
    public String fuelName;

    @SerializedName("AlarmTime")
    public String alarmTime;

    @SerializedName("AlarmInfo")
    public int alarmInfo;
}
