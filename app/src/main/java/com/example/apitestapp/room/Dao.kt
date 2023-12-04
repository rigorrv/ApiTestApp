package com.example.apitestapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.apitestapp.model.Step
import com.example.apitestapp.model.Steppers


@Dao
interface Dao {

    @Query("SELECT * FROM steppers")
    suspend fun getSteppers(): Steppers


    @Query("SELECT * FROM step")
    suspend fun getStep(): Step?

    @Insert(onConflict = REPLACE)
    suspend fun insertSteppers(vararg steppers: Steppers)


    @Insert(onConflict = REPLACE)
    suspend fun insertStep(vararg step: Step)

    @Update
    suspend fun updateStep(vararg step: Step)
}