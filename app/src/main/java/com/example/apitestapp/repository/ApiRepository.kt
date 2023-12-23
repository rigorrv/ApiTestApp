package com.example.apitestapp.repository

import com.example.apitestapp.model.MovieDB
import com.example.apitestapp.model.info.MovieInfoDB
import retrofit2.Response

interface ApiRepository {

    suspend fun getMovie(): Response<MovieDB>
    suspend fun searchMovie(search: String): Response<MovieDB>
    suspend fun getMovieInfo(search: String): Response<MovieInfoDB>
}