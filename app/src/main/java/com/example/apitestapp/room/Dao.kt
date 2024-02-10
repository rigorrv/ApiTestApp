package com.example.apitestapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.apitestapp.model.Steppers

@Dao
interface Dao {

    @Query("SELECT * FROM Steppers")
    suspend fun getSteppers(): Steppers?

    @Insert(onConflict = REPLACE)
    suspend fun insertSteppers(steppers: Steppers)
}