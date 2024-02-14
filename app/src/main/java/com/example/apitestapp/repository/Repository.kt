package com.example.apitestapp.repository

import com.example.apitestapp.api.Api
import com.example.apitestapp.model.Steppers
import com.example.apitestapp.room.Dao
import javax.inject.Inject

open class Repository @Inject constructor(private val api: Api, private val dao: Dao) {

    suspend fun getData() = api.getData()

    suspend fun getSteppers() = dao.getSteppers()

    suspend fun insertSteppers(steppers: Steppers) = dao.insertSteppers(steppers)
}