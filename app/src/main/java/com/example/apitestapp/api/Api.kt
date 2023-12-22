package com.example.apitestapp.api

import com.example.apitestapp.model.MovieDB
import com.example.apitestapp.utilities.ApplicationConstants.endPoint
import com.example.apitestapp.utilities.ApplicationConstants.searchEndPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {

    @GET(endPoint)
    suspend fun getMovies(@Header("Authorization") value: String): Response<MovieDB>

    @GET(searchEndPoint)
    suspend fun searchMovie(
        @Query("query") search: String,
        @Header("Authorization") value: String
    ): Response<MovieDB>
}