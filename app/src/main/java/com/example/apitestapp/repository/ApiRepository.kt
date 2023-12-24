package com.example.apitestapp.repository

import com.example.apitestapp.model.content.MovieDB
import com.example.apitestapp.model.content.Result
import com.example.apitestapp.model.movieinfo.MovieInfoDB
import retrofit2.Response

interface ApiRepository {

    suspend fun getMovie(): Response<MovieDB>
    suspend fun searchMovie(search: String): Response<MovieDB>
    suspend fun movieInfo(movieID: Int?): Response<Result>
}