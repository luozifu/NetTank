package com.miaoxing.nettank.ui.info.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author : Luozifu
 * @date : 2018/10/20
 */

public class RecordResponse implements Serializable {
    @SerializedName("StationID")
    public String stationID;

    @SerializedName("TankID")
    public int tankID;

    @SerializedName("Volume")
    public float volume;

    @SerializedName("AddBefore")
    public float addBefore;

    @SerializedName("AddAfter")
    public float addAfter;

    @SerializedName("Operator")
    public String operator;

    @SerializedName("UpdateTime")
    public String updateTime;

    @SerializedName("TankName")
    public String tankName;

    @SerializedName("FuelName")
    public String fuelName;

}
