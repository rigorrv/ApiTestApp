package com.example.apitestapp.api

import com.example.apitestapp.model.MovieDB
import com.example.apitestapp.utilities.ApplicationConstants.endPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface Api {

    @GET(endPoint)
    suspend fun getMovies(@Header("Authorization") value: String): Response<MovieDB>
}