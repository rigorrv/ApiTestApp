package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.apitestapp.R
import com.example.apitestapp.model.cart.Cart
import com.example.apitestapp.model.content.Content
import com.example.apitestapp.utilities.ApplicationConstants.imagePath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieInfo(
    movieInfo: Content?,
    preload: Boolean,
    steppers: MutableMap<Int?, Int>,
    addCart: (content: Content?, cart: Cart?, action: String) -> Unit,
    nav: () -> Boolean
) {
    val myRating = remember { mutableStateOf(3) }
    if (preload) {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            androidx.compose.material.CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = Color.Black,
            )
        }
    } else {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.movie_info),
                    textAlign = TextAlign.Center
                )
            },
                navigationIcon = {
                    IconButton(onClick = { nav.invoke() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.back_buton
                            )
                        )
                    }
                }
            )
            Image(
                painter = rememberImagePainter(data = imagePath + movieInfo?.poster_path),
                contentDescription = movieInfo?.title, Modifier.padding(20.dp)
            )
            Text(
                text = movieInfo?.title.toString(),
                textAlign = TextAlign.Center, fontSize = 20.sp
            )
            Text(
                text = movieInfo?.vote_average.toString(),
                textAlign = TextAlign.Center, fontSize = 16.sp
            )
            movieInfo?.vote_average?.toInt()?.let {
                RatingBar(
                    currentRating = it,
                    modifierRow = Modifier.padding(10.dp),
                    modifierIcon = Modifier.size(20.dp)
                )
            }
            Steppers(
                info = movieInfo,
                steppers = steppers,
                addCart = { content: Content?, cart: Cart?, action: String ->
                    addCart.invoke(
                        content,
                        cart,
                        action
                    )
                })
            Text(
                text = movieInfo?.overview.toString(),
                Modifier.padding(horizontal = 20.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}