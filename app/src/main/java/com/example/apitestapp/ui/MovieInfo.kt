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
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.imagePath

@Composable
fun MovieInfo(
    info: Result?,
    steppers: MutableMap<Int, Int>,
    stepperClick: (id: Int, action: String) -> Unit,
    navClick: () -> Unit
) {
    info?.let {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Image(
                    imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                    Modifier
                        .weight(2f)
                        .clickable { navClick.invoke() }
                )
                Text(
                    text = info.title.toString(),
                    Modifier.weight(8f),
                    textAlign = TextAlign.Center
                )
                Box(modifier = Modifier.weight(2f))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberImagePainter(data = imagePath + info.poster_path),
                    contentDescription = info.title, Modifier.padding(bottom = 20.dp)
                )
                Steppers(info = info, steppers = steppers, click = { id, action ->
                    stepperClick.invoke(id, action)
                })
                Text(
                    text = info.overview,
                    Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}