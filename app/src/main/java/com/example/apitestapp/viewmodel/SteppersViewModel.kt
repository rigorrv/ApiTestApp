package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.repository.Repository
import com.example.apitestapp.model.Steppers
import com.example.apitestapp.utilities.ApplicationConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SteppersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val stepper = MutableStateFlow(mutableMapOf<Int, Int>())
    private var movieId = mutableListOf<Int>()

    fun insertStepper(id: Int, action: String) {
        viewModelScope.launch {
            repository.getSteppers()?.stepper?.let { movieId = it }
            when (action) {
                ApplicationConstants.AddSteppers -> movieId.add(id)
                ApplicationConstants.RemoveSteppers -> movieId.remove(id)
                ApplicationConstants.RemoveAllSteppers -> while (movieId.contains(id)) movieId.remove(
                    id
                )
                ApplicationConstants.DeletSteppers -> movieId.clear()
            }
            repository.insertSteppers(Steppers(stepper = movieId))
            getSteppers()
        }
    }

    init {
        getSteppers()
    }

    private fun getSteppers() {
        viewModelScope.launch {
            repository.getSteppers()?.stepper?.groupingBy { it }?.eachCount()?.toMutableMap()?.let {
                stepper.value = it
            }
        }
    }
}