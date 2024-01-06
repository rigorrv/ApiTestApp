package com.example.apitestapp.repository

import com.example.apitestapp.model.cart.Steppers

interface DaoRepository {

    suspend fun getCart(): Steppers?
    suspend fun insertCart(steppers: Steppers?)
}