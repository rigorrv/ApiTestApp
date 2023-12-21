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

    private val _moviesStateFlow = MutableStateFlow<MovieDB?>(MovieDB())
    val moviesStateFlow: StateFlow<MovieDB?> = _moviesStateFlow

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            repository.getMovie().apply {
                if (this.isSuccessful) {
                    _moviesStateFlow.value = this.body()
                }
            }
        }
    }
}