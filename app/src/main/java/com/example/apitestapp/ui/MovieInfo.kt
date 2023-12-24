package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.R
import com.example.apitestapp.model.content.Result
import com.example.apitestapp.utilities.ApplicationConstants.imagePath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieInfo(
    movieInfo: Result?,
    cart: MutableMap<Result?, Int>,
    addCart: (Result?, String) -> Unit,
    nav: () -> Boolean
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(title = {
            Text(
                text = movieInfo?.title.toString(),
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
        Steppers(
            item = movieInfo,
            cart = cart,
            addCart = { movie: Result?, action: String -> addCart.invoke(movie, action) })
        Text(
            text = movieInfo?.overview.toString(),
            Modifier.padding(20.dp),
            textAlign = TextAlign.Center
        )
    }
}