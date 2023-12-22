package com.example.apitestapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cart")
data class Cart(
    @PrimaryKey
    val id: Int = 0,
    val cart: MutableList<Result>?
)