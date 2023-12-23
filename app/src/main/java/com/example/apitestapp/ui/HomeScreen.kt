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
    cart: MutableMap<Result?, Int>,
    addCart: (Result?, String) -> Unit,
    searchMovie: (search: String) -> Unit
) {
    val navController = rememberNavController()
    val index = remember {
        mutableStateOf(0)
    }
    NavHost(
        navController = navController,
        startDestination = ComposeNavigation.MovieListNav.route,
        builder = {
            composable(ComposeNavigation.MovieListNav.route) {
                MovieList(
                    info,
                    cart,
                    addCart = { movie: Result, action: String ->
                        addCart.invoke(movie, action)
                    },
                    searchMovie = { search: String -> searchMovie.invoke(search) },
                    nav =
                    { int: Int ->
                        index.value = int
                        navController.navigate(ComposeNavigation.MovieInfoNav.route)
                    },
                    payment = { navController.navigate(ComposeNavigation.CheckoutNav.route) }
                )
            }
            composable(ComposeNavigation.MovieInfoNav.route) {
                MovieInfo(
                    info[index.value],
                    cart,
                    addCart = { movie: Result, action: String -> addCart.invoke(movie, action) },
                    nav = { navController.popBackStack() }
                ) {
                    navController.navigate(ComposeNavigation.CheckoutNav.route)
                }
            }
            composable(ComposeNavigation.CheckoutNav.route) {
                Checkout(
                    cart,
                    addCart,
                    payment = { navController.navigate(ComposeNavigation.PaymentNav.route) },
                    nav = { navController.popBackStack() },
                    infoNav = { int: Int ->
                        index.value = int
                        navController.navigate(ComposeNavigation.MovieInfoNav.route)
                    }
                )
            }
            composable(ComposeNavigation.PaymentNav.route) {
                Payment(
                    cart,
                    addCart = { movie: Result?, action: String -> addCart.invoke(movie, action) }
                ) { navController.navigate(ComposeNavigation.MovieListNav.route) }
            }
        }
    )
}