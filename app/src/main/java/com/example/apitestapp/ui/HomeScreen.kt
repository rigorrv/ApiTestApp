package com.example.apitestapp.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.productImage
import com.example.apitestapp.utilities.BitmapPreview

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeScreen(
    info: List<Result>,
    steppers: MutableMap<Int, Int>,
    clickStepper: (id: Int, action: String) -> Unit,
    getMovie: (movie: String?) -> Unit
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
            modifier = Modifier.background(color = Color.White),
            builder = {
                composable(ComposeNavigation.MovieList.route) {
                    MovieList(info, steppers, clickStepper, nav = {
                        index.value = it
                        navController.navigate(ComposeNavigation.MovieInfo.route)
                    },
                        checkoutNav = { navController.navigate(ComposeNavigation.Checkout.route) },
                        getMovie = { movie: String? ->
                            getMovie.invoke(movie)
                        })
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
                        payment = {
                            Log.d("TAG", "youPayFor: $steppers")
                            navController.navigate(ComposeNavigation.Payment.route)
                        },
                        clickInfo = {
                            index.value = it
                            navController.navigate(ComposeNavigation.MovieInfo.route)
                        }
                    )
                }
                composable(ComposeNavigation.Payment.route) {
                    Payment(info, steppers, clickStepper) { navController.popBackStack() }
                }
                composable(ComposeNavigation.ImageConverter.route) {
                    BitmapPreview(productImage)
                }
            })
    }
}