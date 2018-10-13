package com.miaoxing.nettank.model;

/**
 * 油罐信息
 * @author : Administrator
 * @Date : 2018/10/13 0013
 */
public class Tank {
    /**
     * 油站id
     */
    public String stationId;
    /**
     * 油罐id
     */
    public int tankId;
    /**
     * 油罐名称
     */
    public String tankName;
    /**
     * 油品名称
     */
    public String fuleName;
    /**
     * 状态:0掉线，1在线
     */
    public int status;
    /**
     * 总容量
     */
    public double capacity;
    /**
     * 当前油量
     */
    public double fuelVol;
    /**
     * 当前水量
     */
    public double waterVol;
    /**
     * 温度
     */
    public double temperature;
    /**
     * 油浮子高度
     */
    public double fuelLevel;
    /**
     * 水浮子高度
     */
    public double waterLevel;
    /**
     * 最后更新时间
     */
    public String updateTime;

}
