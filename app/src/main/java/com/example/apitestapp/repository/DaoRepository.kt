package com.example.apitestapp.repository

import com.example.apitestapp.model.Cart

interface DaoRepository {

    suspend fun getCart(): Cart?
    suspend fun insertCart(movie: Cart)
}