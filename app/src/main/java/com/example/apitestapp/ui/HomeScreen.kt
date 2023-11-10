package com.example.apitestapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apitestapp.viewmodel.MovieViewModel

@Composable
fun HomeScreen(movieViewModel: MovieViewModel) {
    val navController = rememberNavController()
    val index = remember {
        mutableStateOf(0)
    }
    movieViewModel.movieStateFlow.collectAsState().value?.results?.let { info ->
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            NavHost(
                navController = navController,
                startDestination = ComposeNavigation.MovieList.route,
            ) {
                composable(ComposeNavigation.MovieList.route) {
                    MovieList(info = info, click = {
                        index.value = it
                        navController.navigate(ComposeNavigation.MovieInfo.route)
                    })
                }
                composable(ComposeNavigation.MovieInfo.route) {
                    MovieInfo(info = info, navController = navController, index = index.value)
                }
            }
        }
    }
}