package com.example.apitestapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.apitestapp.model.Result
import com.example.apitestapp.ui.HomeScreen
import com.example.apitestapp.viewmodel.CartViewModel
import com.example.apitestapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }

    private val cartViewModel: CartViewModel by lazy {
        ViewModelProvider(this)[CartViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val info = viewModel.moviesStateFlow.collectAsState().value?.results
            val cart = cartViewModel.cartStateFlow.collectAsState().value
            info?.let {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    HomeScreen(
                        info = info,
                        cart = cart,
                        getMovie = { movie ->
                            viewModel.getMovies(movie)
                        }
                    ) { item: Result?, action: String -> cartViewModel.insertCart(item, action) }
                }
            }
        }
    }
}