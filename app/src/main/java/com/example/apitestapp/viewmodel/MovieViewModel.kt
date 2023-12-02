package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.api.Repository
import com.example.apitestapp.model.MovieDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _movieStateFlow = MutableStateFlow<MovieDB?>(MovieDB())
    val movieStateFlow: StateFlow<MovieDB?> = _movieStateFlow

    var counterStateFlow = MutableStateFlow(mutableMapOf<Int?, Int>())
    private val idMovie = mutableListOf<Int?>(0, 0)

    init {
        getMovie()
    }

    fun addCounter(id: Int?) {
        idMovie.add(id)
        val listCounter = idMovie.groupingBy { it }.eachCount().toMutableMap()
        counterStateFlow.value = listCounter
    }

    private fun getMovie() {
        viewModelScope.launch {
            repository.getMovie().apply {
                if (this.isSuccessful) {
                    _movieStateFlow.value = this.body()
                }
            }
        }
    }
}