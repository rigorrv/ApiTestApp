package com.example.apitestapp.repository

import com.example.apitestapp.api.Api
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api)  {

    suspend fun getData() = api.getData()
}