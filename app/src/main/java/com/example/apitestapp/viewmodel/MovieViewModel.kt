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

    val _movieStateFlow = MutableStateFlow<MovieDB?>(MovieDB())
    var movieStateFlow: StateFlow<MovieDB?> = _movieStateFlow

    init {
        getMovie()
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