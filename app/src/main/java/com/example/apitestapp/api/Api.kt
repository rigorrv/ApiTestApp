package com.example.apitestapp.api

import com.example.apitestapp.model.ContentDB
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getData(): Response<ContentDB?>
}