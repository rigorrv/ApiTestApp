package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.cart.Cart
import com.example.apitestapp.model.cart.Steppers
import com.example.apitestapp.model.content.Content
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

    private val _cartStateFlow = MutableStateFlow<MutableMap<Cart?, Int>>(mutableMapOf())
    val cartStateFlow: StateFlow<MutableMap<Cart?, Int>> = _cartStateFlow
    private var cart = mutableListOf<Cart?>()

    private val _steppersStateFlow = MutableStateFlow<MutableMap<Int?, Int>>(mutableMapOf())
    val stepperStateFlow: StateFlow<MutableMap<Int?, Int>> = _steppersStateFlow
    private var stepper = mutableListOf<Int?>()

    fun addCart(content: Content? = null, cart: Cart? = null, action: String) {
        viewModelScope.launch {
            repository.getCart()?.cart?.let { this@CartViewModel.cart = it }
            repository.getCart()?.steppers?.let { stepper = it }
            val movieId: Int? = if (content != null) content.id else cart?.id
            val item: Cart? = content?.run {
                Cart(
                    adult = adult,
                    id = id,
                    original_language = original_language,
                    original_title = original_title,
                    overview = overview,
                    poster_path = poster_path,
                    release_date = release_date,
                    title = title,
                    video = video
                )
            }
                ?: cart
            when (action) {
                AddCart -> {
                    stepper.add(movieId)
                    this@CartViewModel.cart.add(item)
                }
                RemoveCart -> {
                    stepper.remove(movieId)
                    this@CartViewModel.cart.remove(item)
                }
                DeleteCart -> {
                    while (stepper.contains(movieId)) stepper.remove(movieId)
                    while (this@CartViewModel.cart.contains(item)) this@CartViewModel.cart.remove(
                        item
                    )
                }
                ClearCart -> {
                    stepper.clear()
                    this@CartViewModel.cart.clear()
                }
            }
            repository.insertCart(Steppers(cart = this@CartViewModel.cart, steppers = stepper))
            getCart()
            getSteppers()
        }
    }

    init {
        getCart()
        getSteppers()
    }

    private fun getSteppers() {
        viewModelScope.launch {
            repository.getCart()?.steppers?.groupingBy { it }?.eachCount()?.toMutableMap()?.let {
                _steppersStateFlow.value = it
            }
        }
    }

    private fun getCart() {
        viewModelScope.launch {
            repository.getCart()?.cart?.groupingBy { it }?.eachCount()?.toMutableMap()?.let {
                _cartStateFlow.value = it
            }
        }
    }
}