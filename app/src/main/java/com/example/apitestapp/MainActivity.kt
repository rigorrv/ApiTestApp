package com.example.apitestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.example.apitestapp.ui.HomeScreen
import com.example.apitestapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val info = viewModel.movieStateFlow.collectAsState().value?.results
            val steppers = viewModel.counter.collectAsState().value
            HomeScreen(info = info, steppers = steppers, addStepper = { id, action ->
                viewModel.counterStepper(id, action)
            })
        }
    }
}