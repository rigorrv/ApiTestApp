package com.example.apitestapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.apitestapp.model.Steppers


@Dao
interface Dao {

    @Query("SELECT * FROM steppers")
    suspend fun getSteppers(): Steppers?

    @Insert(onConflict = REPLACE)
    suspend fun insertStep(vararg step: Steppers)

    @Query("DELETE FROM steppers")
    suspend fun delete()

}