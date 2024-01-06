package com.example.apitestapp.model.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cart")
data class Steppers(
    @PrimaryKey
    val id: Int = 0,
    val cart: MutableList<Cart?>,
    val steppers: MutableList<Int?>
)