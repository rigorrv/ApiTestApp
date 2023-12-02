package com.example.apitestapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apitestapp.model.Result
import com.example.apitestapp.viewmodel.MovieViewModel

@Composable
fun HomeScreen(
    info: List<Result?>?,
    movieViewModel: MovieViewModel
) {
    val navController = rememberNavController()
    val index = remember {
        mutableStateOf(0)
    }
    info?.let {
        NavHost(
            navController = navController,
            startDestination = ComposeNavigation.MovieList.rout,
            builder = {
                composable(ComposeNavigation.MovieList.rout) {
                    MovieList(
                        info = info,
                        movieViewModel
                    ) {
                        index.value = it
                        navController.navigate(ComposeNavigation.MovieInfo.rout)
                    }
                }
                composable(ComposeNavigation.MovieInfo.rout) {
                    MovieInfo(info = info[index.value], navController = navController,movieViewModel)
                }
            }
        )
    }
}