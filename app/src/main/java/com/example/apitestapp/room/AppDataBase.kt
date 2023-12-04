package com.example.apitestapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apitestapp.model.Step
import com.example.apitestapp.model.SteppConverter
import com.example.apitestapp.model.StepperConverter
import com.example.apitestapp.model.Steppers

@Database(entities = [Steppers::class, Step::class], version = 1)
@TypeConverters(
    StepperConverter::class,
    SteppConverter::class
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDao(): Dao
}