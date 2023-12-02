package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apitestapp.R
import com.example.apitestapp.model.Result
import com.example.apitestapp.viewmodel.MovieViewModel

@Composable
fun CounterCompose(viewModel: MovieViewModel, item: Result?) {
    val counterState = viewModel.counterStateFlow.collectAsState()
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
                    viewModel.addCounter(item?.id)
                }
        )
    }
}