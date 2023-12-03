package com.example.apitestapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(
    info: List<Result?>?,
    stepper: MutableMap<Int, Int>,
    stepperClick: (id: Int, action: String) -> Unit,
    navClick: (int: Int) -> Unit,
    checkout: () -> Unit
) {
    info?.let {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                Modifier.weight(9f),
                content = {
                    itemsIndexed(info) { index, item ->
                        BoxWithConstraints {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = rememberImagePainter(data = thumbPath + item?.poster_path),
                                    contentDescription = item?.title,
                                    Modifier
                                        .width(200.dp)
                                        .height(200.dp)
                                        .clickable { navClick.invoke(index) }
                                )
                                Text(text = item?.title.toString(), textAlign = TextAlign.Center)
                            }
                            Box(
                                Modifier
                                    .width(200.dp)
                                    .padding(20.dp),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Steppers(
                                    info = item,
                                    steppers = stepper,
                                    click = { id, action ->
                                        stepperClick.invoke(id, action)
                                    })
                            }
                        }
                    }
                })
            if (!stepper.isNullOrEmpty()) {
                Column(
                    Modifier
                        .weight(1.5f)
                        .padding(20.dp)
                ) {
                    Row(
                        Modifier
                            .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
                            .fillMaxWidth()
                            .padding(20.dp)
                            .clickable {
                                checkout.invoke()
                            }
                    ) {
                        Text(
                            text = "Checkout " + stepper.size,
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}