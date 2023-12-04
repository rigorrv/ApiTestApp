package com.example.apitestapp.api

import com.example.apitestapp.model.MovieDB
import com.example.apitestapp.model.Steppers
import retrofit2.Response

interface DaoInterface {

    suspend fun getMovie(): Response<MovieDB>
    suspend fun getSteppers(): Steppers?
    suspend fun insertSteppers(steppers: Steppers)
}