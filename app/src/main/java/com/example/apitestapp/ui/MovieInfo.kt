package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.R
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.imagePath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieInfo(
    info: Result,
    cart: MutableMap<Result?, Int>?,
    nav: () -> Unit,
    addCart: (Result?, String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CenterAlignedTopAppBar(
            title = { Text(info.title) },
            Modifier.background(color = Color.White),
            navigationIcon = {
                IconButton(onClick = {
                    nav.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_buton)
                    )
                }
            }
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberImagePainter(data = imagePath + info.poster_path),
                contentDescription = info.title,
                Modifier.padding(20.dp)
            )
            Steppers(
                item = info,
                cart,
            ) { item: Result?, action: String -> addCart.invoke(item,action) }
            Text(text = info.overview, Modifier.padding(20.dp), textAlign = TextAlign.Center)
        }
    }
}