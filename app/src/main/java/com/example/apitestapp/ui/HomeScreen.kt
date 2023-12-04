package com.example.apitestapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apitestapp.model.Result

@Composable
fun HomeScreen(
    info: List<Result>,
    steppers: MutableMap<Int, Int>,
    clickSteppers: (id: Int, action: String) -> Unit
) {
    val navController = rememberNavController()
    val index = remember {
        mutableStateOf(0)
    }
    NavHost(
        navController = navController,
        startDestination = ComposeNavigation.MovieList.route,
        builder = {
            composable(ComposeNavigation.MovieList.route) {
                MovieList(
                    info = info,
                    steppers = steppers,
                    clickSteppers = { id, action -> clickSteppers.invoke(id, action) },
                    nav = {
                        index.value = it
                        navController.navigate(ComposeNavigation.MovieInfo.route)
                    }) {
                    navController.navigate(ComposeNavigation.Checkout.route)
                }
            }
            composable(ComposeNavigation.MovieInfo.route) {
                MovieInfo(
                    info = info[index.value],
                    steppers = steppers,
                    clickStepper = { id, action -> clickSteppers.invoke(id, action) }) {
                    navController.popBackStack()
                }
            }
            composable(ComposeNavigation.Checkout.route) {
                Checkout(
                    info = info,
                    steppers = steppers,
                    clickStepper = { id, action -> clickSteppers.invoke(id, action) },
                    nav = { navController.popBackStack() }) {
                    navController.navigate(ComposeNavigation.Payment.route)
                }
            }
            composable(ComposeNavigation.Payment.route) {
                Payment(
                    nav = { navController.popBackStack() },
                    clickStepper = { id, action -> clickSteppers.invoke(id, action) })
            }
        }
    )
}