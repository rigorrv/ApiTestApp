package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.api.Repository
import com.example.apitestapp.model.MovieDB
import com.example.apitestapp.model.Steppers
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

    private val _moviesStateFlow = MutableStateFlow<MovieDB?>(MovieDB())
    val moviesStateFlow: StateFlow<MovieDB?> = _moviesStateFlow
    val stepper = MutableStateFlow(mutableMapOf<Int, Int>())
    private var movieId = mutableListOf<Int>()
    fun insertStepper(id: Int, action: String) {
        viewModelScope.launch {
            repository.getSteppers()?.stepper?.let { movieId = it }
            when (action) {
                AddSteppers -> movieId.add(id)
                RemoveSteppers -> movieId.remove(id)
                RemoveAllSteppers -> while (movieId.contains(id)) movieId.remove(id)
                DeletSteppers -> movieId.clear()
            }
            repository.insertSteppers(Steppers(stepper = movieId))
            getSteppers()
        }
    }

    init {
        getSteppers()
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

    private fun getSteppers() {
        viewModelScope.launch {
            stepper.value =
                repository.getSteppers()?.stepper?.groupingBy { it }?.eachCount()?.toMutableMap()!!
        }
    }
}