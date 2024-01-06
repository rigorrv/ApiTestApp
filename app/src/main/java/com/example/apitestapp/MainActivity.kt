package com.example.apitestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.example.apitestapp.model.content.Content
import com.example.apitestapp.model.cart.Cart
import com.example.apitestapp.ui.HomeScreen
import com.example.apitestapp.viewmodel.CartViewModel
import com.example.apitestapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val movieViewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }

    private val cartViewModel: CartViewModel by lazy {
        ViewModelProvider(this)[CartViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val info = movieViewModel.movieStateFlow.collectAsState().value?.results
            val cart = cartViewModel.cartStateFlow.collectAsState().value
            val steppers = cartViewModel.stepperStateFlow.collectAsState().value
            val movieInfo = movieViewModel.movieInStateFlow.collectAsState().value
            val preload = movieViewModel.preload.collectAsState().value

            info?.let {
                HomeScreen(
                    info,
                    cart,
                    steppers,
                    movieInfo,
                    searchMovie = { movie: String? -> movieViewModel.getMovie(movie) },
                    addCart = { movie: Content?, cart: Cart?, action: String ->
                        cartViewModel.addCart(
                            movie = movie,
                            cart = cart,
                            action = action
                        )
                    },
                    getMovieInfo = { movieId: Int? ->
                        movieViewModel.movieInfo(movieId)
                    },
                    preload
                )
            }
        }
    }
}