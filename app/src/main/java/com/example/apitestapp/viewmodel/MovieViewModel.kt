package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.content.Content
import com.example.apitestapp.model.content.MovieDB
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

    private val _movieInfoStateFlow = MutableStateFlow<Content?>(null)
    val movieInStateFlow: StateFlow<Content?> = _movieInfoStateFlow

    val preload = MutableStateFlow<Boolean>(false)

    init {
        getMovie(null)
    }

    fun getMovie(movie: String?) {
        preload.value = true
        if (movie.isNullOrEmpty()) {
            viewModelScope.launch {
                repository.getMovie().apply {
                    _movieStateFlow.value = this
                    preload.value = false
                }
            }
        } else {
            viewModelScope.launch {
                repository.searchMovie(movie).apply {
                    if (this.isSuccessful) {
                        _movieStateFlow.value = this.body()
                        preload.value = false
                    }
                }
            }
        }
    }

    fun movieInfo(movieID: Int?) {
        preload.value = true
        viewModelScope.launch {
            repository.movieInfo(movieID).apply {
                if (this.isSuccessful) {
                    _movieInfoStateFlow.value = this.body()
                    preload.value = false
                }
            }
        }
    }
}