package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.Steppers
import com.example.apitestapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollegeSteppersVM @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val _schoolSteppers = MutableStateFlow<MutableList<String>>(mutableListOf())
    val schoolSteppers: StateFlow<MutableList<String>> = _schoolSteppers
    private var school = mutableListOf<String>()

    fun addSteppers(contentDBItem: String?, action: String) {
        viewModelScope.launch {
            repository.getSteppers()?.let { school = it.content }
            when (action) {
                "AddSteppers" -> contentDBItem?.let { school.add(it) }
                "RemoveSteppers" -> school.remove(contentDBItem)
                "Delete" -> school.clear()
            }
            repository.insertSteppers(
                Steppers(content = school)
            )
            getSteppers()
        }
    }

    init {
        getSteppers()
    }

    private fun getSteppers() {
        viewModelScope.launch {
            repository.getSteppers()?.content?.apply {
                _schoolSteppers.value = this
            }
        }
    }
}