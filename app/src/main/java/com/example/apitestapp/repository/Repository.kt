package com.example.apitestapp.repository

import com.example.apitestapp.api.Api
import com.example.apitestapp.model.cart.Cart
import com.example.apitestapp.model.content.MovieDB
import com.example.apitestapp.model.content.Result
import com.example.apitestapp.room.Dao
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api, private val dao: Dao) : ApiRepository,
    DaoRepository {

    override suspend fun getMovie(): Response<MovieDB> = api.getMovie()

    override suspend fun searchMovie(search: String): Response<MovieDB> = api.searchMovie(search)

    override suspend fun movieInfo(movieID: Int?): Response<Result> = api.movieInfo(movieID)

    override suspend fun getCart(): Cart? = dao.getCart()

    override suspend fun insertCart(cart: Cart) = dao.insertCart(cart)

}