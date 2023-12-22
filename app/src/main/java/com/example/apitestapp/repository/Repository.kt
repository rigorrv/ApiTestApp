package com.example.apitestapp.repository

import com.example.apitestapp.api.Api
import com.example.apitestapp.model.Cart
import com.example.apitestapp.model.MovieDB
import com.example.apitestapp.room.Dao
import com.example.apitestapp.utilities.ApplicationConstants.header
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api, private val dao: Dao) : ApiRepository,
    DaoRepository {

    override suspend fun getMovie(): Response<MovieDB> = api.getMovies(header)

    override suspend fun searchMovie(search: String): Response<MovieDB> = api.searchMovie(search,header)

    override suspend fun getCart(): Cart? = dao.getCart()

    override suspend fun insertCart(cart: Cart) = dao.insertCart(cart)

}