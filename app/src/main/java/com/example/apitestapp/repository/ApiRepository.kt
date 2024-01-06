package com.example.apitestapp.repository

import com.example.apitestapp.model.content.Content
import com.example.apitestapp.model.content.MovieDB
import retrofit2.Response

interface ApiRepository {

    suspend fun getMovie(): MovieDB?
    suspend fun searchMovie(search: String): Response<MovieDB>
    suspend fun movieInfo(movieID: Int?): Response<Content>
}