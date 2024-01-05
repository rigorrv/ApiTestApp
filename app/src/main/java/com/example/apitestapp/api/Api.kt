package com.example.apitestapp.api

import com.example.apitestapp.model.content.Content
import com.example.apitestapp.model.content.MovieDB
import com.example.apitestapp.model.content.Result
import com.example.apitestapp.utilities.ApplicationConstants.endPoint
import com.example.apitestapp.utilities.ApplicationConstants.movieInfo
import com.example.apitestapp.utilities.ApplicationConstants.searchEndPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET(endPoint)
    suspend fun getMovie(): Response<MovieDB?>

    @GET(searchEndPoint)
    suspend fun searchMovie(
        @Query("query")
        search: String
    ): Response<MovieDB>

    @GET(movieInfo)
    suspend fun movieInfo(
        @Path("movieId")
        movieID: Int?
    ): Response<Content>
}