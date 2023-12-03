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
import com.example.apitestapp.utilities.ApplicationConstants.RemoveAll

@Composable
fun Payment(
    addStepper: (id: Int?, action: String) -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        Image(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            Modifier.clickable {
                addStepper(0, RemoveAll)
            })
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "YOU Pay NOW, \n YOUR LIST WILL BE CLEAR NOW", textAlign = TextAlign.Center)
    }
}