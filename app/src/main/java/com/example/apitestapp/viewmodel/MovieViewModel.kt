package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.api.Repository
import com.example.apitestapp.model.MovieDB
import com.example.apitestapp.utilities.ApplicationConstants.AddStepper
import com.example.apitestapp.utilities.ApplicationConstants.Delet
import com.example.apitestapp.utilities.ApplicationConstants.RemoveStepper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _movieStateFlow = MutableStateFlow<MovieDB?>(MovieDB())
    val movieStateFlow: StateFlow<MovieDB?> = _movieStateFlow
    private var movieId = mutableListOf<Int>()
    val counter = MutableStateFlow(mutableMapOf<Int, Int>())

    fun counterStepper(id: Int?, action: String) {
        id?.let {
            when (action) {
                RemoveStepper -> movieId.remove(id)
                AddStepper -> movieId.add(id)
                Delet -> while (movieId.contains(id)) {
                    movieId.remove(id)
                }
            }
            val list = movieId.groupingBy { it }.eachCount().toMutableMap()
            counter.value = list
        }
    }

    init {
        getMovie()
    }

    fun getMovie() {
        viewModelScope.launch {
            repository.getMovie().apply {
                if (this.isSuccessful) {
                    _movieStateFlow.value = this.body()
                }
            }
        }
    }

}