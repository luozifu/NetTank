package com.miaoxing.nettank.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 油
 * @author : Administrator
 * @Date : 2018/10/13 0013
 */
public class Fuel implements Serializable{
    /**
     * 油品名称
     */
    @SerializedName("FuelName")
    public String fuelName;
    /**
     * 当前油量
     */
    @SerializedName("sum_fuelVol")
    public double fuelVol;

    /**
     * 总容量
     */
    @SerializedName("sum_capacity")
    public double capacity;
}
