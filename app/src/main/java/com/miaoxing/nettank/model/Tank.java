package com.miaoxing.nettank.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 油罐信息
 * @author : Administrator
 * @Date : 2018/10/13 0013
 */
public class Tank implements Serializable{
    /**
     * 油罐名称
     */
    @SerializedName("TankName")
    public String tankName;
    /**
     * 油品名称
     */
    @SerializedName("FuelName")
    public String fuelName;
    /**
     * 状态:0掉线，1在线
     */
    @SerializedName("Status")
    public int status;
    /**
     * 总容量
     */
    @SerializedName("Capacity")
    public double capacity;
    /**
     * 当前油量
     */
    @SerializedName("FuelVol")
    public double fuelVol;
    /**
     * 当前水量
     */
    @SerializedName("WaterVol")
    public double waterVol;
    /**
     * 温度
     */
    public double temperature;
    /**
     * 油浮子高度
     */
    @SerializedName("FuelLevel")
    public double fuelLevel;
    /**
     * 水浮子高度
     */
    @SerializedName("WaterLevel")
    public double waterLevel;

}
