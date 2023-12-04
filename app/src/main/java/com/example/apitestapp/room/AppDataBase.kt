package com.example.apitestapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apitestapp.model.Steppers
import com.example.apitestapp.model.SteppConverter

@Database(entities = [Steppers::class], version = 1)
@TypeConverters(
    SteppConverter::class
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDao(): Dao
}