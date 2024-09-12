package com.example.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.database.R
import com.example.database.dao.UserInterestDao
import com.example.database.entity.UserInterestEntity

@Database(entities = [UserInterestEntity::class], version = 1, exportSchema = false)
abstract class MeetingDatabase : RoomDatabase() {

    abstract fun getUserInterestDao(): UserInterestDao

    companion object {
        private var instance: MeetingDatabase? = null

        fun getInstance(context: Context): MeetingDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MeetingDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MeetingDatabase::class.java,
                context.getString(R.string.database_name)
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}