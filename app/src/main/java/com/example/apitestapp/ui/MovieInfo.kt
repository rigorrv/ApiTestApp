package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.R
import com.example.apitestapp.model.content.Content
import com.example.apitestapp.model.content.Result
import com.example.apitestapp.utilities.ApplicationConstants.imagePath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieInfo(
    movieInfo: Content?,
    preload: Boolean,
    steppers: MutableMap<Int?, Int>,
    addCart: (content: Content?, result: Result?, action: String) -> Unit,
    nav: () -> Boolean
) {
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
                info = movieInfo,
                steppers = steppers,
                addCart = { content: Content?, result: Result?, action: String ->
                    addCart.invoke(
                        content,
                        result,
                        action
                    )
                })
            Text(
                text = movieInfo?.overview.toString(),
                Modifier.padding(20.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}