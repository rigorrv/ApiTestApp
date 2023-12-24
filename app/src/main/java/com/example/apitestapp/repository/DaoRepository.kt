package com.example.apitestapp.repository

import com.example.apitestapp.model.cart.Cart

interface DaoRepository {

    suspend fun getCart(): Cart?
    suspend fun insertCart(cart: Cart)
}