package com.example.apitestapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.Step
import com.example.apitestapp.model.Steppers
import com.example.apitestapp.room.Dao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val dao: Dao) : ViewModel() {

    val steppersStateFlow = MutableStateFlow<Steppers?>(null)
    val stepper = MutableStateFlow(mutableMapOf<Int, Int>())
    private var movieId = mutableListOf<Int>()
    fun insertStepper(id: Int, action: String) {
        viewModelScope.launch {
            dao.getStep()?.stepper?.let { movieId = it }
            movieId.add(id)
            dao.insertStep(Step(stepper = movieId))
            getSteppers()
        }
    }

    init {
        getSteppers()
    }

    private fun getSteppers() {
        viewModelScope.launch {
            if (!dao.getStep()?.stepper.isNullOrEmpty())
                stepper.value =
                    dao.getStep()?.stepper?.groupingBy { it }?.eachCount()?.toMutableMap()!!
        }
    }
}