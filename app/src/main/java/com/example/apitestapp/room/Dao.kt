package com.example.apitestapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.apitestapp.model.cart.Cart
import com.example.apitestapp.model.content.MovieDB

@Dao
interface Dao {

    @Query("SELECT * FROM Content")
    suspend fun getMovie(): MovieDB?

    @Insert(onConflict = REPLACE)
    suspend fun insertMovie(vararg movieDB: MovieDB?)

    @Query("SELECT * FROM Cart")
    suspend fun getCart(): Cart?

    @Insert(onConflict = REPLACE)
    suspend fun insertCart(vararg cart: Cart?)
}