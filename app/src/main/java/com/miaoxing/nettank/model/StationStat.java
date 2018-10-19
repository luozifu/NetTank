package com.miaoxing.nettank.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author : Luozifu
 * @date : 2018/10/18
 */

public class StationStat implements Serializable {

    @SerializedName("station_count")
    public int stationCount;

    @SerializedName("station_down")
    public int stationDown;

    @SerializedName("station_up")
    public int stationUp;

}
