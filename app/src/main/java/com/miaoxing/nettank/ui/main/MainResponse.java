package com.miaoxing.nettank.ui.main;

import com.google.gson.annotations.SerializedName;
import com.miaoxing.nettank.model.Fuel;
import com.miaoxing.nettank.model.Station;
import com.miaoxing.nettank.model.StationStat;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Luozifu
 * @date : 2018/10/18
 */

public class MainResponse implements Serializable{

    @SerializedName("tank_stat")
    public List<Fuel> mFuelList;

    @SerializedName("station_stat")
    public StationStat mStationStat;

    @SerializedName("station_list")
    public List<Station> mStationList;
}
