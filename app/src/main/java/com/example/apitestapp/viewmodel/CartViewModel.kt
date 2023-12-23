package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.Cart
import com.example.apitestapp.model.Result
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
    var movieId = mutableListOf<Result?>()

    fun addCart(movie: Result?, action: String) {
        viewModelScope.launch {
            repository.getCart()?.cart?.let { movieId = it }
            when (action) {
                AddCart -> movieId.add(movie)
                RemoveCart -> movieId.remove(movie)
                DeleteCart -> while (movieId.contains(movie)) movieId.remove(movie)
                ClearCart -> movieId.clear()
            }
            repository.insertCart(Cart(cart = movieId))
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
        }
    }
}