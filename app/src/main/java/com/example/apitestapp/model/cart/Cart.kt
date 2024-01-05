package com.example.apitestapp.model.cart

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apitestapp.model.content.Result

@Entity(tableName = "Cart")
data class Cart(
    @PrimaryKey
    val id: Int = 0,
    val cart: MutableList<Result?>,
    val steppers: MutableList<Int?>
)