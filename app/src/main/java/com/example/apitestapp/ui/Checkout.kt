package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.DeleteCart
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Checkout(
    cartSteppers: MutableMap<Result?, Int>?,
    nav: () -> Unit,
    payment: () -> Unit,
    clickInfo: (int: Int) -> Unit,
    addCart: (Result?, String) -> Unit
) {
    if (cartSteppers.isNullOrEmpty()) {
        LaunchedEffect(key1 = 1) {
            nav.invoke()
        }
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CenterAlignedTopAppBar(
            title = { Text("Checkout") },
            Modifier.background(color = Color.White),
            navigationIcon = {
                IconButton(onClick = {
                    nav.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            }
        )
        cartSteppers?.keys?.let { cart ->
            LazyColumn(
                Modifier
                    .weight(8f)
                    .padding(20.dp),
                content = {
                    itemsIndexed(cart.toList()) { index, item ->
                        Row(
                            Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberImagePainter(data = thumbPath + item?.poster_path),
                                contentDescription = item?.title,
                                Modifier
                                    .weight(2f)
                                    .width(100.dp)
                                    .height(100.dp)
                                    .clickable {
                                        clickInfo.invoke(index)
                                    }
                            )
                            Text(
                                text = item?.title.toString(),
                                Modifier
                                    .weight(7f)
                                    .padding(20.dp)
                            )
                            Steppers(
                                item = item,
                                cartSteppers,
                            ) { item: Result?, action: String -> addCart.invoke(item, action) }
                            Image(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delet",
                                Modifier
                                    .padding(start = 20.dp)
                                    .clickable {
                                        addCart.invoke(
                                            item, DeleteCart
                                        )
                                    })
                        }
                    }
                })
        }
        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                Modifier
                    .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
                    .clickable { payment.invoke() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Payment",
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}