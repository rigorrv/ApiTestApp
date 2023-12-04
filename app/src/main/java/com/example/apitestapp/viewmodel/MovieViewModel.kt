package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.api.Repository
import com.example.apitestapp.model.MovieDB
import com.example.apitestapp.utilities.ApplicationConstants.AddSteppers
import com.example.apitestapp.utilities.ApplicationConstants.DeletSteppers
import com.example.apitestapp.utilities.ApplicationConstants.RemoveAllSteppers
import com.example.apitestapp.utilities.ApplicationConstants.RemoveSteppers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _movieStateFlow = MutableStateFlow<MovieDB?>(MovieDB())
    val movieStateFlow: StateFlow<MovieDB?> = _movieStateFlow
    var movieId = mutableListOf<Int>()
    val steppers = MutableStateFlow(mutableMapOf<Int, Int>())

    init {
        getMovie()
    }

    fun steppers(id: Int, action: String) {
        when (action) {
            RemoveSteppers -> movieId.remove(id)
            AddSteppers -> movieId.add(id)
            RemoveAllSteppers -> while (movieId.contains(id)) movieId.remove(id)
            DeletSteppers -> movieId.clear()
        }
        val list = movieId.groupingBy { it }.eachCount().toMutableMap()
        steppers.value = list
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