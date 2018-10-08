package com.miaoxing.nettank.database.user;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Date : 2018/10/06
 * @Desc : 用户表
 */
@Entity
public class User {

    @PrimaryKey
    @NonNull
    public String uid;

    public String phone;

    public String token;

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
