package com.example.apitestapp.repository

import com.example.apitestapp.model.MovieDB
import retrofit2.Response

interface ApiRepository {

    suspend fun getMovie(): Response<MovieDB>
    suspend fun searchMovie(search : String): Response<MovieDB>
}