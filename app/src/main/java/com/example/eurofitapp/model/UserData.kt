package com.example.eurofitapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserData(
    @PrimaryKey val id: Int = 1,
    val age: Int,
    val height: Double,
    val weight: Double,
    val gender: String,
    val password: String
)
