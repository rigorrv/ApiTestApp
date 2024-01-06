package com.example.apitestapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.apitestapp.model.cart.Steppers

@Dao
interface Dao {

    @Query("SELECT * FROM Cart")
    suspend fun getCart(): Steppers?

    @Insert(onConflict = REPLACE)
    suspend fun insertCart(vararg steppers: Steppers?)
}