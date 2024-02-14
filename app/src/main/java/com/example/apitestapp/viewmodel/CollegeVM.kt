package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.ContentDB
import com.example.apitestapp.repository.RetrofitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CollegeVM(private val repository: RetrofitRepository) : ViewModel() {

    private val _collegeStateFlow = MutableStateFlow<ContentDB?>(null)
    val collegeStateFlow: StateFlow<ContentDB?> = _collegeStateFlow

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            repository.getData().apply {
                _collegeStateFlow.value = this
            }
        }
    }

}