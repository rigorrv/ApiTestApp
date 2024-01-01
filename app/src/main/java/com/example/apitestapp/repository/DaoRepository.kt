package com.example.apitestapp.repository

import com.example.apitestapp.model.cart.Cart
import com.example.apitestapp.model.content.MovieDB

interface DaoRepository {

    suspend fun getCart(): Cart?
    suspend fun insertCart(cart: Cart?)
}