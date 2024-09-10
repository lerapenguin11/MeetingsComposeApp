package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.UserInterestEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInterestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUserInterests(vararg interest: UserInterestEntity): List<Long>

    @Query("SELECT * FROM user_interests")
    fun getUserInterests(): Flow<List<UserInterestEntity>>
}