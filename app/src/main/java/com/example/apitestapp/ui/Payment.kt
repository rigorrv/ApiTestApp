package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Payment(
    info: List<Result>,
    steppers: MutableMap<Int, Int>,
    clickSteppers: (id: Int, action: String) -> Unit, nav: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            title = { Text("Payment") },
            Modifier.background(color = Color.White),
            navigationIcon = {
                IconButton(onClick = {
                    clickSteppers.invoke(0, ApplicationConstants.DeletSteppers)
                    nav.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            }
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "You Paid For", textAlign = TextAlign.Center)
            LazyColumn(
                Modifier
                    .weight(8f)
                    .padding(20.dp),
                content = {
                    itemsIndexed(info) { index, item ->
                        if (steppers.containsKey(item.id)) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = ApplicationConstants.thumbPath + item.poster_path),
                                    contentDescription = item.title,
                                    Modifier
                                        .weight(2f)
                                        .width(100.dp)
                                        .height(100.dp)
                                )
                                Text(
                                    text = item.title,
                                    Modifier
                                        .weight(7f)
                                        .padding(20.dp)
                                )
                            }
                        }
                    }
                })
        }
    }
}