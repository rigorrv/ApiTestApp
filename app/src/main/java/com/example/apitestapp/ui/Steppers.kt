package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import com.example.apitestapp.model.ContentDBItem

@Composable
fun Steppers(
    item: ContentDBItem?,
    steppers: MutableList<String>,
    addSteppers: (String?, String) -> Unit
) {
    item?.let {
        if (steppers.contains(item.dbn))
            Image(
                imageVector = Icons.Default.Check,
                contentDescription = "CheckButton",
                Modifier.clickable {
                    addSteppers.invoke(item.dbn, "RemoveSteppers")
                },
                colorFilter = ColorFilter.tint(Color.Green)
            )
        else
            Image(
                imageVector = Icons.Default.Check,
                contentDescription = "CheckButton",
                Modifier.clickable {
                    addSteppers.invoke(item.dbn, "AddSteppers")
                },
                colorFilter = ColorFilter.tint(Color.Gray)
            )
    }
}