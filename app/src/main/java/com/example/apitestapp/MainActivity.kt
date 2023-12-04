package com.example.apitestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
            val info = viewModel.moviesStateFlow.collectAsState().value?.results
            val steppers = viewModel.stepper.collectAsState().value
            info?.let {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    HomeScreen(info = info, steppers = steppers, clickStepper = { id, action ->
                        viewModel.insertStepper(id, action)
                    })
                }
            }
        }
    }
}