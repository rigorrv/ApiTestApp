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

    private val vieMode: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val info = vieMode.movieStateFlow.collectAsState().value?.results
            HomeScreen(info, vieMode)
        }
    }
}