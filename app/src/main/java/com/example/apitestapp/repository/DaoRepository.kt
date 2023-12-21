package com.example.apitestapp.repository

import com.example.apitestapp.model.Steppers

interface DaoRepository {

    suspend fun getSteppers(): Steppers?
    suspend fun insertSteppers(steppers: Steppers)
}