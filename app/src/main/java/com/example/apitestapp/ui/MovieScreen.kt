package com.example.apitestapp.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apitestapp.model.cart.Cart
import com.example.apitestapp.model.content.Content


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    info: List<Content>,
    cart: MutableMap<Cart?, Int>,
    steppers: MutableMap<Int?, Int>,
    searchMovie: (String) -> Unit,
    addCart: (content: Content?, cart: Cart?, action: String) -> Unit,
    getMovieInfo: (Int) -> Unit,
    checkout: () -> Unit,
    lastSearch: String,
) {

    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Home", "About", "Settings", "More", "Something", "Everything")

    Column(modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            edgePadding = 0.dp
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        when (index) {
                            0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                            1 -> Icon(imageVector = Icons.Default.Info, contentDescription = null)
                            2 -> Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null
                            )
                            3 -> Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                            4 -> Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null
                            )
                            5 -> Icon(imageVector = Icons.Default.Star, contentDescription = null)
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> {
                MovieList(
                    info,
                    cart,
                    steppers,
                    searchMovie = { movie: String ->
                        searchMovie.invoke(movie)
                    },
                    addCart = { content: Content?, cart: Cart?, action: String ->
                        addCart.invoke(
                            content,
                            cart,
                            action
                        )
                    },
                    getMovieInfo = { movieId: Int ->
                        getMovieInfo.invoke(movieId)
                    },
                    checkout = { checkout.invoke() },
                    lastSearch = lastSearch
                )
            }
        }
    }
}
