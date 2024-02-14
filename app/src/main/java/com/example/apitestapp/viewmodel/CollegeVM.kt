package com.example.apitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitestapp.model.ContentDB
import com.example.apitestapp.repository.Repository
import com.example.apitestapp.repository.RetrofitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CollegeVM @Inject constructor(private val repository: Repository) : ViewModel() {

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