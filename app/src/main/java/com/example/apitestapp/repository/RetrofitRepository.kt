package com.example.apitestapp.repository

import com.example.apitestapp.api.Api
import com.example.apitestapp.model.ContentDB
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepository : ApiRepository {

    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    var api = providesRetrofit().create(Api::class.java)

    override suspend fun getData(): ContentDB? = api.getData().body()


}

interface ApiRepository {
    suspend fun getData(): ContentDB?
}