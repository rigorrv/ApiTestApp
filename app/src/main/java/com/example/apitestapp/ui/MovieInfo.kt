package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.imagePath

@Composable
fun MovieInfo(info: Result?, navController: NavController) {
    info?.let {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(Modifier.fillMaxWidth()) {
                Image(
                    imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                    Modifier
                        .weight(2f)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(text = info.title, Modifier.weight(8f), textAlign = TextAlign.Center)
                Box(modifier = Modifier.weight(2f))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberImagePainter(data = imagePath + info.poster_path),
                    contentDescription = info.title
                )
                Text(text = info.overview, textAlign = TextAlign.Center)
            }
        }
    }
}