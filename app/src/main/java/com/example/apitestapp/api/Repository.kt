package com.example.apitestapp.api

import com.example.apitestapp.model.Steppers
import com.example.apitestapp.room.Dao
import com.example.apitestapp.utilities.ApplicationConstants.header
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api, private val dao: Dao) {

    suspend fun getMovie() = api.getMovies(header)
    suspend fun getSteppers() = dao.getStep()
    suspend fun insertSteppers(steppers: Steppers) = dao.insertStep(steppers)
    suspend fun delet() = dao.delete()
}