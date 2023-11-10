package com.example.apitestapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(info: List<Result>, click: (int: Int) -> Unit) {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
            itemsIndexed(info) { index, item ->
                Column(
                    Modifier.clickable {
                        click.invoke(index)
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(data = thumbPath + item.poster_path),
                        contentDescription = item.title,
                        Modifier
                            .height(200.dp)
                            .width(200.dp)
                    )
                    Text(text = item.title)
                }
            }
        })
    }
}