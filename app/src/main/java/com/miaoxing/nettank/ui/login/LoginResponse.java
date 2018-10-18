package com.miaoxing.nettank.ui.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author : Luozifu
 * @date : 2018/10/18
 */

public class LoginResponse implements Serializable{
    @SerializedName("Right")
    private int right;

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
