package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.RemoveAllSteppers
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Checkout(
    info: List<Result>,
    steppers: MutableMap<Int, Int>,
    clickStepper: (id: Int, action: String) -> Unit,
    nav: () -> Unit,
    navPayment: () -> Unit
) {
    if (steppers.isNullOrEmpty()) {
        LaunchedEffect(key1 = 1) {
            nav.invoke()
        }
    }
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            Modifier
                .weight(8f)
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clickable {
                        nav.invoke()
                    }
            ) {
                Image(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            LazyColumn(
                content = {
                    itemsIndexed(info) { index, item ->
                        if (steppers.contains(item.id)) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = thumbPath + item.poster_path),
                                    contentDescription = item.title,
                                    Modifier
                                        .width(100.dp)
                                        .height(100.dp)
                                        .weight(2f)
                                )
                                Text(
                                    text = item.title,
                                    Modifier
                                        .weight(6f)
                                        .padding(20.dp)
                                )
                                Steppers(
                                    info = item,
                                    steppers = steppers,
                                    clickStepper = { id, action ->
                                        clickStepper.invoke(
                                            id,
                                            action
                                        )
                                    })
                                Image(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delet",
                                    Modifier
                                        .padding(start = 20.dp)
                                        .clickable {
                                            clickStepper.invoke(
                                                item.id,
                                                RemoveAllSteppers
                                            )
                                        })
                            }
                        }
                    }
                })
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
            ) {
                Text(
                    text = "Payment",
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clickable { navPayment.invoke() },
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}