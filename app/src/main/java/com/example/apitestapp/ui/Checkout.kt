package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.R
import com.example.apitestapp.model.content.Content
import com.example.apitestapp.model.content.Result
import com.example.apitestapp.utilities.ApplicationConstants.DeleteCart
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Checkout(
    cart: MutableMap<Result?, Int>,
    steppers: MutableMap<Int?, Int>,
    addCart: (content: Content?, result: Result?, action: String) -> Unit,
    nav: () -> Boolean,
    getMovieInfo: (Int?) -> Unit,
    payment: () -> Unit,
) {
    if (cart.isNullOrEmpty()) {
        LaunchedEffect(key1 = 1) {
            nav.invoke()
        }
    }
    cart.keys.toList().let { info ->
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.checkout),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { nav.invoke() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_buton)
                        )
                    }
                }
            )
            LazyColumn(
                Modifier.weight(8f),
                content = {
                    itemsIndexed(info) { index, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            verticalAlignment = CenterVertically
                        ) {
                            Image(
                                painter = rememberImagePainter(data = thumbPath + item?.poster_path),
                                contentDescription = item?.title,
                                Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .weight(2f)
                            )
                            Text(
                                text = item?.title.toString(), modifier = Modifier
                                    .padding(12.dp)
                                    .weight(6f)
                                    .clickable {
                                        getMovieInfo.invoke(item?.id)
                                    }
                            )
                            SteppersCheckout(
                                cart = item,
                                steppers = steppers,
                                addCart = { content: Content?, result: Result?, action: String ->
                                    addCart.invoke(
                                        content,
                                        result,
                                        action
                                    )
                                })
                            Image(
                                imageVector = Icons.Default.Delete,
                                contentDescription = stringResource(
                                    id = R.string.remove
                                ),
                                Modifier
                                    .padding(end = 12.dp)
                                    .clickable {
                                        addCart.invoke(null, item, DeleteCart)
                                    }
                            )
                        }
                    }
                })
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1.5f)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .background(color = Color.Black, RoundedCornerShape(20.dp))
                        .clickable {
                            payment.invoke()
                        }
                ) {
                    Text(
                        text = stringResource(id = R.string.payment),
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
    }
}