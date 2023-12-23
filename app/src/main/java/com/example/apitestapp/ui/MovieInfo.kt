package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.imagePath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieInfo(
    item: Result,
    cart: MutableMap<Result?, Int>,
    addCart: (Result, String) -> Unit,
    nav: () -> Boolean,
    navCheckout: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.checkout), Modifier.padding(10.dp))
            },
            Modifier
                .weight(1f)
                .padding(top = 20.dp),
            navigationIcon = {
                IconButton(onClick = { nav.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_buton)
                    )
                }
            },
        )
        Column(
            Modifier
                .weight(7f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(data = imagePath + item.poster_path),
                contentDescription = item.title,
                Modifier.padding(20.dp)
            )
            Steppers(
                item = item,
                cart = cart,
                addCart =
                { movie: Result, action: String -> addCart.invoke(movie, action) })
            Text(
                text = item.overview.toString(),
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                textAlign = TextAlign.Center
            )
        }
        Column(Modifier.weight(1f)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
            ) {
                Text(
                    text = stringResource(id = R.string.checkout),
                    Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .clickable { navCheckout.invoke() },
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}