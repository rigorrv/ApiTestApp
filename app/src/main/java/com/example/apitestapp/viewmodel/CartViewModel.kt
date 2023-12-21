package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.Cart
import com.example.apitestapp.model.Result
import com.example.apitestapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _cartStateFlow = MutableStateFlow<Cart?>(null)
    val cartStateFlow: StateFlow<Cart?> = _cartStateFlow
    var cart = mutableSetOf<Result>()

    fun insertCart(result: Result) {
        viewModelScope.launch {
            repository.getCart()?.cart?.let { cart = it }
            cart.add(result)
            repository.insertCart(Cart(cart = cart))
        }
        getCart()
    }

    init {
        getCart()
    }

    private fun getCart() {
        viewModelScope.launch {
            repository.getCart()?.let {
                _cartStateFlow.value = it
            }
        }
    }

}