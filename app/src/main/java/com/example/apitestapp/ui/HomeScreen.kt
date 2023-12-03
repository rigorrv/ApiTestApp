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
    info: List<Result?>?,
    steppers: MutableMap<Int, Int>,
    addStepper: (id: Int?, action: String) -> Unit,
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
                    MovieList(info = info, stepper = steppers, stepperClick = { id, action ->
                        addStepper.invoke(id, action)
                    }, navClick = {
                        index.value = it
                        navController.navigate(ComposeNavigation.MovieInfo.route)
                    }) {
                        navController.navigate(ComposeNavigation.Checkout.route)
                    }
                }
                composable(ComposeNavigation.MovieInfo.route) {
                    MovieInfo(
                        info = info?.get(index.value),
                        steppers = steppers,
                        stepperClick = { id, action -> addStepper.invoke(id, action) }) {
                        navController.popBackStack()
                    }
                }
                composable(ComposeNavigation.Checkout.route) {
                    Checkout(
                        info = info,
                        steppers = steppers,
                        navBack = { navController.popBackStack() },
                        click = {
                            index.value = it
                            navController.navigate(ComposeNavigation.MovieInfo.route)
                        },
                        stepperClick = { id, action ->
                            addStepper.invoke(id, action)
                        }, backHome = {
                            navController.navigate(ComposeNavigation.MovieList.route)
                        }
                    ) {
                        navController.navigate(ComposeNavigation.Payment.route)
                    }
                }
                composable(ComposeNavigation.Payment.route) {
                    Payment(
                        addStepper = { id, action ->
                            navController.navigate(ComposeNavigation.MovieList.route)
                            addStepper(0, action)
                        }
                    )
                }
            }
        )
}
