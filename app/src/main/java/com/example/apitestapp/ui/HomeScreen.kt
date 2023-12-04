package com.example.apitestapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apitestapp.model.Result

@Composable
fun HomeScreen(
    info: List<Result>,
    steppers: MutableMap<Int, Int>,
    clickStepper: (id: Int, action: String) -> Unit
) {
    val navController = rememberNavController()
    val index = remember {
        mutableStateOf(0)
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        NavHost(
            navController = navController,
            startDestination = ComposeNavigation.MovieList.route,
            builder = {
                composable(ComposeNavigation.MovieList.route) {
                    MovieList(info, steppers, clickStepper, nav = {
                        index.value = it
                        navController.navigate(ComposeNavigation.MovieInfo.route)
                    }) {
                        navController.navigate(ComposeNavigation.Checkout.route)
                    }
                }
                composable(ComposeNavigation.MovieInfo.route) {
                    MovieInfo(
                        info[index.value],
                        steppers,
                        clickStepper,
                    ) { navController.popBackStack() }
                }
                composable(ComposeNavigation.Checkout.route) {
                    Checkout(
                        info,
                        steppers,
                        clickStepper,
                        nav = { navController.popBackStack() },
                        payment = { navController.navigate(ComposeNavigation.Payment.route) })
                }
                composable(ComposeNavigation.Payment.route) {
                    Payment(info,steppers, clickStepper) { navController.popBackStack() }
                }
            })
    }
}