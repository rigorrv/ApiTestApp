package com.example.apitestapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apitestapp.model.Cart
import com.example.apitestapp.model.CartConverter

@Database(entities = [Cart::class], version = 1)
@TypeConverters(
    CartConverter::class
)
abstract class DB : RoomDatabase() {
    abstract fun getDao(): Dao
}