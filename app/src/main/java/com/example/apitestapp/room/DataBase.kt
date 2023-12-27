package com.example.apitestapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apitestapp.model.cart.Cart
import com.example.apitestapp.model.cart.CartConverter
import com.example.apitestapp.model.content.MovieDB

@Database(entities = [Cart::class, MovieDB::class], version = 1)
@TypeConverters(
    CartConverter::class
)
abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): Dao
}