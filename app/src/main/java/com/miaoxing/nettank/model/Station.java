package com.miaoxing.nettank.model;

import java.util.List;

/**
 * 油站信息
 * @author : Administrator
 * @Date : 2018/10/13 0013
 */
public class Station {
    /**
     * 油站id
     */
    public String stationId;
    /**
     * 油站名称
     */
    public String stationName;
    /**
     * 包含的油品
     */
    public List<Fuel> fuelList;

}
