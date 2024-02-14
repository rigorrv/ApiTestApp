package com.example.apitestapp.repository

import com.example.apitestapp.api.Api
import com.example.apitestapp.model.ContentDB
import com.example.apitestapp.model.Steppers
import com.example.apitestapp.room.Dao
import java.io.IOException
import javax.inject.Inject

open class Repository @Inject constructor(private val api: Api, private val dao: Dao) {

    suspend fun getData(): ContentDB? {
        return try {
            api.getData().let {
                if (it.isSuccessful) it.body() else null
            }
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getSteppers() = dao.getSteppers()

    suspend fun insertSteppers(steppers: Steppers) = dao.insertSteppers(steppers)
}