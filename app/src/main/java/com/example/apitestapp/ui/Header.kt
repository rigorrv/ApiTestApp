package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apitestapp.model.Result

@Composable
fun Header(info: Result?, navController: NavController, title: String) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
            Modifier
                .weight(2f)
                .clickable {
                    navController.popBackStack()
                }
        )
        (if (info?.title.isNullOrEmpty()) {
            title
        } else {
            info?.title
        })?.let {
            Text(
                text = it,
                Modifier
                    .weight(8f)
                    .padding(top = 20.dp, bottom = 20.dp),
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier.weight(2f))
    }
}