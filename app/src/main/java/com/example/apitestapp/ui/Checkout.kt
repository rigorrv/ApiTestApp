package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath
import com.example.apitestapp.viewmodel.MovieViewModel

@Composable
fun Checkout(
    info: List<Result?>,
    viewModel: MovieViewModel, navController: NavController
) {
    val counterState = viewModel.counterStateFlow.collectAsState()
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(title = "Checkout", navController = navController, info = null)
        LazyColumn(content = {
            itemsIndexed(info) { index, item ->
                if (counterState.value.containsKey(item?.id)) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = rememberImagePainter(data = thumbPath + item?.poster_path),
                            contentDescription = item?.title,
                            Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .weight(3f)
                        )
                        Text(text = item?.title.toString(), Modifier.weight(7f))
                        Text(
                            text = counterState.value.filterKeys { it == item?.id }.values.joinToString(),
                            Modifier.weight(3f)
                        )
                    }
                }
            }
        })
    }
}