package com.example.apitestapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Steppers")
data class Steppers(
    @PrimaryKey
    val id: Int = 0,
    val content: MutableList<String?>
)