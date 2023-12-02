package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.imagePath
import com.example.apitestapp.viewmodel.MovieViewModel

@Composable
fun MovieInfo(info: Result?, navController: NavController, movieViewModel: MovieViewModel) {
    info?.let {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(info = info, navController = navController, "")
            Column(
                Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberImagePainter(data = imagePath + info.poster_path),
                    contentDescription = info.title, Modifier.padding(bottom = 20.dp)
                )
                CounterCompose(viewModel = movieViewModel, item = info)
                Text(
                    text = info.overview,
                    Modifier.padding(top = 20.dp), textAlign = TextAlign.Center
                )
            }
        }
    }
}