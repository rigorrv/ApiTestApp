package com.example.apitestapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apitestapp.model.cart.Steppers
import com.example.apitestapp.model.cart.CartConverter
import com.example.apitestapp.model.cart.StepperConverter

@Database(entities = [Steppers::class], version = 1)
@TypeConverters(
    CartConverter::class,
    StepperConverter::class
)
abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): Dao
}