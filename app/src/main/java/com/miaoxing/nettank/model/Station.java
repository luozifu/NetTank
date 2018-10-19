package com.miaoxing.nettank.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 油站信息
 * @author : Administrator
 * @Date : 2018/10/13 0013
 */
public class Station implements Serializable{
    /**
     * 油站id
     */
    @SerializedName("StationID")
    public String stationId;
    /**
     * 油站名称
     */
    @SerializedName("StationName")
    public String stationName;

    /**
     * 油站状态
     */
    @SerializedName("Status")
    public int status;

    /**
     * 包含的油品
     */
    @SerializedName("tank")
    public List<Fuel> fuelList;

}
