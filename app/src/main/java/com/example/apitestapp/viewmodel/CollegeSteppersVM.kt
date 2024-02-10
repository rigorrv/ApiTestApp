package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.ContentDB
import com.example.apitestapp.model.Steppers
import com.example.apitestapp.repository.Repository
import com.example.apitestapp.utilities.AndroidUtilities.AddAll
import com.example.apitestapp.utilities.AndroidUtilities.AddSteppers
import com.example.apitestapp.utilities.AndroidUtilities.Delete
import com.example.apitestapp.utilities.AndroidUtilities.RemoveAll
import com.example.apitestapp.utilities.AndroidUtilities.RemoveSteppers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollegeSteppersVM @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val _schoolSteppers = MutableStateFlow<MutableList<String?>>(mutableListOf())
    val schoolSteppers: StateFlow<MutableList<String?>> = _schoolSteppers
    private var school = mutableListOf<String?>()

    fun addAllColleges(content: ContentDB, action: String) {
        viewModelScope.launch {
            repository.getSteppers()?.let { school = it.content }
            when (action) {
                AddAll -> school = content.map { it?.dbn }.toMutableList()
                RemoveAll -> school.clear()
            }
            repository.insertSteppers(
                Steppers(content = school)
            )
            getSteppers()
        }
    }

    fun addSteppers(contentDBItem: String?, action: String) {
        viewModelScope.launch {
            repository.getSteppers()?.let { school = it.content }
            when (action) {
                AddSteppers -> contentDBItem?.let { school.add(it) }
                RemoveSteppers -> school.remove(contentDBItem)
                Delete -> school.clear()
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