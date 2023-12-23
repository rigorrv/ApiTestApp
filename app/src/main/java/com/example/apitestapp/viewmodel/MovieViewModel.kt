package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.MovieDB
import com.example.apitestapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _movieStateFlow = MutableStateFlow<MovieDB?>(null)
    val movieStateFlow: StateFlow<MovieDB?> = _movieStateFlow

    init {
        getMovie(null)
    }

    fun getMovie(search: String?) {
        if (search.isNullOrEmpty()) {
            viewModelScope.launch {
                repository.getMovie().apply {
                    if (this.isSuccessful) {
                        _movieStateFlow.value = this.body()
                    }
                }
            }
        } else {
            viewModelScope.launch {
                repository.searchMovie(search).apply {
                    if (this.isSuccessful) {
                        _movieStateFlow.value = this.body()
                    }
                }
            }
        }
    }
}