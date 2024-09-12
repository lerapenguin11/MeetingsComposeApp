package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_interests")
data class UserInterestEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String
)
