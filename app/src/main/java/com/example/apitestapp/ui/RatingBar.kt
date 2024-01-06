package com.example.apitestapp.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    maxRating: Int = 5,
    currentRating: Int,
) {
    Row(Modifier.padding(10.dp)) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= currentRating / 2) Icons.Filled.Star
                else Icons.Filled.Star,
                contentDescription = null,
                tint = if (i <= currentRating / 2) Color.Black
                else Color.Gray
            )
        }
    }
}