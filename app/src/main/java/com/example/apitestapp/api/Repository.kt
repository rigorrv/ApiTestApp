package com.example.apitestapp.api

import com.example.apitestapp.utilities.ApplicationConstants.header
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) {

    suspend fun getMovie() = api.getMovie(header)
}