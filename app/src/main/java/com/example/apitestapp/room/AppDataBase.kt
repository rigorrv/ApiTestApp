package com.example.apitestapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apitestapp.model.Cart
import com.example.apitestapp.model.CartConverter
import com.example.apitestapp.model.SteppConverter
import com.example.apitestapp.model.Steppers

@Database(entities = [Steppers::class, Cart::class], version = 1)
@TypeConverters(
    SteppConverter::class,
    CartConverter::class
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDao(): Dao
}