package com.miaoxing.nettank.net;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author : Luozifu
 * @date : 2018/10/18
 */

public class Result<T> implements Serializable {

    private int code;

    @SerializedName("msg")
    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
