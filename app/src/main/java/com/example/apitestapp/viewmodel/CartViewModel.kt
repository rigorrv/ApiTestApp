package com.example.apitestapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.cart.Cart
import com.example.apitestapp.model.content.Content
import com.example.apitestapp.model.content.Result
import com.example.apitestapp.repository.Repository
import com.example.apitestapp.utilities.ApplicationConstants.AddCart
import com.example.apitestapp.utilities.ApplicationConstants.ClearCart
import com.example.apitestapp.utilities.ApplicationConstants.DeleteCart
import com.example.apitestapp.utilities.ApplicationConstants.RemoveCart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _cartStateFlow = MutableStateFlow<MutableMap<Result?, Int>>(mutableMapOf())
    val cartStateFlow: StateFlow<MutableMap<Result?, Int>> = _cartStateFlow
    private var cart = mutableListOf<Result?>()

    private val _steppersStateFlow = MutableStateFlow<MutableMap<Int?, Int>>(mutableMapOf())
    val stepperStateFlow: StateFlow<MutableMap<Int?, Int>> = _steppersStateFlow
    private var stepper = mutableListOf<Int?>()

    fun addCart(movie: Content? = null, result: Result? = null, action: String) {
        viewModelScope.launch {
            repository.getCart()?.cart?.let { cart = it }
            repository.getCart()?.steppers?.let { stepper = it }
            val id: Int? = if (movie != null) movie.id else result?.id
            val item: Result? = if (movie != null) {
                Result(
                    adult = movie.adult,
                    id = movie.id,
                    original_language = movie.original_language,
                    original_title = movie.original_title,
                    overview = movie.overview,
                    poster_path = movie.poster_path,
                    release_date = movie.release_date,
                    title = movie.title,
                    video = movie.video
                )
            } else {
                result
            }
            when (action) {
                AddCart -> {
                    stepper.add(id)
                    cart.add(item)
                }
                RemoveCart -> {
                    stepper.remove(id)
                    cart.remove(item)
                }
                DeleteCart -> {
                    while (stepper.contains(id)) stepper.remove(id)
                    while (cart.contains(item)) cart.remove(item)
                    Log.d("TAG", "addCart: Deleat Cart")
                }
                ClearCart -> {
                    stepper.clear()
                    cart.clear()
                }
            }
            repository.insertCart(Cart(cart = cart, steppers = stepper))
            getCart()
        }
    }

    init {
        getCart()
    }

    private fun getCart() {
        viewModelScope.launch {
            repository.getCart()?.cart?.groupingBy { it }?.eachCount()?.toMutableMap()?.let {
                _cartStateFlow.value = it
            }
            repository.getCart()?.steppers?.groupingBy { it }?.eachCount()?.toMutableMap()?.let {
                _steppersStateFlow.value = it
            }
        }
    }
}