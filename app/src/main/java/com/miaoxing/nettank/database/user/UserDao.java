package com.miaoxing.nettank.database.user;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 *
 * @Date : 2018/10/06
 * @Desc :
 */
@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE uid IN (:uid)")
    User query(String uid);

    @Update
    void Update(User user);

    @Delete
    void delete(User user);
}
