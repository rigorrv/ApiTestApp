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
    info: List<Result>,
    steppers: MutableMap<Int, Int>,
    clickStepper: (id: Int, action: String) -> Unit,
    nav: (int: Int) -> Unit,
    checkoutNav: () -> Unit
) {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            Modifier
                .weight(8f)
                .padding(vertical = 20.dp),
            content = {
                itemsIndexed(info) { index, item ->
                    BoxWithConstraints {
                        Column(Modifier.clickable {
                            nav.invoke(index)
                        }, horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = rememberImagePainter(data = thumbPath + item.poster_path),
                                contentDescription = item.title,
                                Modifier
                                    .width(200.dp)
                                    .height(200.dp)
                            )
                            Text(
                                text = item.title,
                                Modifier.padding(20.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Steppers(item, steppers, clickStepper)
                        }
                    }
                }
            })
        if (!steppers.isNullOrEmpty())
            Column(
                Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { checkoutNav.invoke() }
                        .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
                ) {
                    Text(
                        text = "Checkout",
                        Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center, color = Color.White
                    )
                }
            }
    }
}