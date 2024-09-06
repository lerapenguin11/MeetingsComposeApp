package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.database.entity.UserInterestEntity

@Dao
interface UserInterestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllInterest(vararg interest: UserInterestEntity): List<Long>
}