package com.example.apitestapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apitestapp.model.ContentConverter
import com.example.apitestapp.model.Steppers

@Database(entities = [Steppers::class], version = 1)
@TypeConverters(
    ContentConverter::class
)
abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): Dao
}