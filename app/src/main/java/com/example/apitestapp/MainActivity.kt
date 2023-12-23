package com.example.apitestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.example.apitestapp.model.Result
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
            info?.let {
                HomeScreen(
                    info,
                    cart,
                    addCart = { movie: Result?, action: String ->
                        cartViewModel.addCart(
                            movie,
                            action
                        )
                    },
                    searchMovie = { search: String -> movieViewModel.getMovie(search) }
                )
            }
        }
    }
}