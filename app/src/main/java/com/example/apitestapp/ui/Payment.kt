package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.apitestapp.utilities.ApplicationConstants.ClearCart
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Payment(
    cart: MutableMap<Result?, Int>, nav: () -> Unit,
    addCart: (movie: Result?, action: String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(title = {
            Text(
                text = stringResource(id = R.string.payment),
                textAlign = TextAlign.Center
            )
        },
            navigationIcon = {
                IconButton(onClick = {
                    nav.invoke()
                    addCart.invoke(null, ClearCart)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_buton)
                    )
                }
            })
        LazyColumn(
            content = {
                itemsIndexed(cart.keys.toList()) { index, item ->
                    Row(
                        Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberImagePainter(data = thumbPath + item?.poster_path),
                            contentDescription = item?.title,
                            Modifier
                                .width(100.dp)
                                .height(100.dp)
                        )
                        Text(text = item?.title.toString())
                    }
                }
            })
    }
}