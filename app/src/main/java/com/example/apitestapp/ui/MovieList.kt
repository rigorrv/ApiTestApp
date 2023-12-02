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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.R
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath
import com.example.apitestapp.viewmodel.MovieViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(
    info: List<Result?>,
    movieViewModel: MovieViewModel,
    click: (int: Int) -> Unit
) {
    val counterState = movieViewModel.counterStateFlow.collectAsState()
    info.let {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                content = {
                    itemsIndexed(info) { index, item ->
                        BoxWithConstraints {
                            Column(
                                Modifier
                                    .clickable { click.invoke(index) },
                                horizontalAlignment = CenterHorizontally
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = thumbPath + item?.poster_path),
                                    contentDescription = item?.title,
                                    Modifier
                                        .width(200.dp)
                                        .height(200.dp)
                                )
                                Text(
                                    text = item?.title.toString(),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .width(200.dp)
                                    .padding(10.dp), contentAlignment = TopEnd
                            ) {
                                Row(
                                    Modifier
                                        .background(
                                            color = Color.Black,
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                        .padding(5.dp)
                                ) {
                                    if (counterState.value.containsKey(item?.id)) {
                                        Image(
                                            painter = painterResource(id = R.drawable.remove),
                                            contentDescription = "remove",
                                        )
                                    }
                                    if (counterState.value.containsKey(item?.id)) {
                                        Text(
                                            text = counterState.value.filterKeys { it == item?.id }.values.joinToString(),
                                            Modifier.width(20.dp),
                                            textAlign = TextAlign.Center,
                                            color = Color.White
                                        )
                                    }
                                    Image(
                                        painterResource(id = R.drawable.add),
                                        contentDescription = "add",
                                        Modifier
                                            .clickable {
                                                movieViewModel.addCounter(item?.id)
                                            }
                                    )
                                }
                            }
                        }
                    }
                })
        }
    }
}