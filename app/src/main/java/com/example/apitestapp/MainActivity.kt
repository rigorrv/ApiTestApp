package com.example.apitestapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.apitestapp.ui.HomeScreen
import com.example.apitestapp.viewmodel.MovieViewModel
import com.example.apitestapp.viewmodel.SteppersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }

    private val steppersViewModel: SteppersViewModel by lazy {
        ViewModelProvider(this)[SteppersViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val info = viewModel.moviesStateFlow.collectAsState().value?.results
            val steppers = steppersViewModel.stepper.collectAsState().value
            info?.let {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    HomeScreen(info = info, steppers = steppers, clickStepper = { id, action ->
                        steppersViewModel.insertStepper(id, action)
                    })
                }
            }
        }
    }
}