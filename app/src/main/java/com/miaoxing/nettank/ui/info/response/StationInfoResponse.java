package com.miaoxing.nettank.ui.info.response;

import com.google.gson.annotations.SerializedName;
import com.miaoxing.nettank.model.Fuel;
import com.miaoxing.nettank.model.Tank;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Luozifu
 * @date : 2018/10/20
 */

public class StationInfoResponse implements Serializable{

    @SerializedName("tank_stat")
    public List<Fuel> mFuelList;

    @SerializedName("update_time")
    public String updateTime;

    @SerializedName("tank_list")
    public List<Tank> mTankList;

}
