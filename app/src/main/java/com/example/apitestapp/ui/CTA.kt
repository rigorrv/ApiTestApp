package com.example.apitestapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CTA(stepper: MutableMap<Int, Int>, checkout: () -> Unit) {
    Row(
        Modifier
            .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                checkout.invoke()
            }
    ) {
        Text(
            text = "Checkout " + stepper.size,
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}