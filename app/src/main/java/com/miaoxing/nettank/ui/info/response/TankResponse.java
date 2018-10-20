package com.miaoxing.nettank.ui.info.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author : Luozifu
 * @date : 2018/10/20
 */

public class TankResponse implements Serializable{

    /**
     * 油罐id
     */
    @SerializedName("TankID")
    public int tankID;
    /**
     * 油罐名称
     */
    @SerializedName("TankName")
    public String tankName;

}
