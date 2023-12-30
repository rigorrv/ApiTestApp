package com.example.apitestapp.ui

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
import com.example.apitestapp.model.content.Result
import com.example.apitestapp.utilities.ApplicationConstants

@Composable
fun HomeScreen(
    info: List<Result>,
    cart: MutableMap<Result?, Int>,
    movieInfo: Result?,
    searchMovie: (String?) -> Unit,
    addCart: (Result?, String) -> Unit,
    getMovieInfo: (Int?) -> Unit,
    preload: Boolean,
) {
    val navController = rememberNavController()
    val search = remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        NavHost(
            navController = navController,
            startDestination = ComposeNavigation.MovieListNav.route,
            builder = {
                composable(ComposeNavigation.MovieListNav.route) {
                    MovieList(
                        info,
                        cart,
                        movieInfo,
                        searchMovie = { movie: String ->
                            search.value = movie
                            searchMovie.invoke(movie)
                        },
                        addCart = { movie: Result?, action: String ->
                            addCart.invoke(
                                movie,
                                action
                            )
                        },
                        getMovieInfo = { movieId: Int ->
                            getMovieInfo.invoke(movieId)
                            navController.navigate(ComposeNavigation.MovieInfoNav.route)
                        },
                        checkout = { navController.navigate(ComposeNavigation.CheckoutNav.route) },
                        search.value,
                    )
                }
                composable(ComposeNavigation.MovieInfoNav.route) {
                    MovieInfo(
                        movieInfo,
                        preload,
                        cart,
                        addCart = { movie: Result?, action: String ->
                            addCart.invoke(
                                movie,
                                action
                            )
                        }
                    ) { navController.popBackStack() }
                }
                composable(ComposeNavigation.CheckoutNav.route) {
                    Checkout(
                        cart,
                        addCart = { movie, action ->
                            addCart.invoke(
                                movie,
                                action
                            )
                        },
                        nav = { navController.popBackStack() },
                        getMovieInfo = { movieId: Int? ->
                            getMovieInfo.invoke(movieId)
                            navController.navigate(ComposeNavigation.MovieInfoNav.route)
                        },
                        payment = { navController.navigate(ComposeNavigation.PaymentNav.route) },
                    )
                }
                composable(ComposeNavigation.PaymentNav.route) {
                    Payment(
                        cart,
                        nav = {
                            search.value = ""
                            searchMovie.invoke(null)
                            navController.navigate(ComposeNavigation.MovieListNav.route)
                            addCart.invoke(null, ApplicationConstants.ClearCart)
                        }
                    )
                }
            }
        )
    }
}