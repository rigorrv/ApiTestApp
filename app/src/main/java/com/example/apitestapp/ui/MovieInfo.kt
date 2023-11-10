package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.imagePath

@Composable
fun MovieInfo(info: List<Result>, navController: NavController, index: Int) {
    info[index].let {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = CenterHorizontally
        ) {
            Row(Modifier.fillMaxWidth()) {
                Image(
                    imageVector = Icons.Default.ArrowBack, contentDescription = it.title,
                    Modifier
                        .weight(3f)
                        .clickable { navController.popBackStack() }
                )
                Text(text = it.title, Modifier.weight(8f), textAlign = TextAlign.Center)
                Box(modifier = Modifier.weight(3f))
            }
            Image(
                painter = rememberImagePainter(data = imagePath + it.poster_path),
                contentDescription = it.title
            )
            Text(text = it.overview, Modifier.padding(top = 20.dp), textAlign = TextAlign.Center)
        }
    }
}