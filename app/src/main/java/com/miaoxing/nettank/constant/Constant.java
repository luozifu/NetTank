package com.miaoxing.nettank.constant;


/**
 * @author : Administrator
 * @Date : 2018/10/7 0007
 */
public class Constant {

    /**
     * 报警开关key
     */
    public static final String PREFERENCES_ALARM_KEY = "alarm";
    /**
     * 选择语言key
     */
    public static final String PREFERENCES_LANGUAGE_KEY = "language";
    /**
     * 用戶key
     */
    public static final String PREFERENCES_USER_KEY = "user";

    /**
     * 成功
     */
    public static final int CODE_SUCCESS = 1;

    /**
     * 失败
     */
    public static final int CODE_FAIL = 0;

    /**
     * 在线状态
     */
    public static final int STATUS_ONLINE = 1;

    /**
     * 水浮子无效
     */
    public static final int ALARM_WATER_FLOAT = 1;
    /**
     * 检查不到探测棒
     */
    public static final int ALARM_ALPENSTOCK = 2;
    /**
     * 低温
     */
    public static final int ALARM_LOW_TMP = 3;
    /**
     * 高温
     */
    public static final int ALARM_HIGH_TMP = 4;
    /**
     * 低水位
     */
    public static final int ALARM_WATER_LOW = 5;
    /**
     * 高水位
     */
    public static final int ALARM_WATER_HIGH = 6;
    /**
     * 低油位
     */
    public static final int ALARM_FUEL_LOW = 7;
    /**
     * 高油位
     */
    public static final int ALARM_FUEL_HIGH = 8;
}
