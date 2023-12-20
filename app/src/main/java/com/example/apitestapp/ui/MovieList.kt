package com.example.apitestapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.productImage
import com.example.apitestapp.utilities.BitmapPreview

@RequiresApi(Build.VERSION_CODES.Q)
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
            GridCells.Fixed(2),
            Modifier
                .weight(8f)
                .padding(vertical = 20.dp),
            content = {
                itemsIndexed(info) { index, item ->
                    BoxWithConstraints {
                        Column(Modifier.clickable {
                            nav.invoke(index)
                        }, horizontalAlignment = Alignment.CenterHorizontally) {
                            //BitmapPreview(thumbPath + item.poster_path) <== MovieDB Images
                            BitmapPreview(productImage) //Product Remove Background
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
                        .background(color = Color.Black, shape = RoundedCornerShape(20.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        Modifier
                            .padding(start = 20.dp)
                            .weight(1.6f)
                            .width(30.dp)
                            .height(30.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(20.dp)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = steppers.values.sum().toString(),
                            textAlign = TextAlign.Center, color = Color.Black
                        )
                    }
                    Text(
                        text = "Checkout",
                        Modifier
                            .weight(8f)
                            .padding(vertical = 20.dp),
                        textAlign = TextAlign.Center, color = Color.White
                    )
                    Box(modifier = Modifier.weight(1.5f))
                }
            }
    }
}