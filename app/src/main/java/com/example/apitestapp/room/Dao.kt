package com.example.apitestapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.apitestapp.model.Step


@Dao
interface Dao {

    @Query("SELECT * FROM step")
    suspend fun getStep(): Step?

    @Insert(onConflict = REPLACE)
    suspend fun insertStep(vararg step: Step)

}