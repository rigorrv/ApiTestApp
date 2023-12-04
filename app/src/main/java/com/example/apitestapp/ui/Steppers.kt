package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apitestapp.R
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.AddSteppers
import com.example.apitestapp.utilities.ApplicationConstants.RemoveSteppers

@Composable
fun Steppers(
    info: Result,
    steppers: MutableMap<Int, Int>,
    clickStepper: (id: Int, action: String) -> Unit
) {
    Row(Modifier.background(color = Color.Black, shape = RoundedCornerShape(20.dp))) {
        if (steppers.containsKey(info.id)) {
            Image(
                painter = painterResource(id = R.drawable.remove),
                contentDescription = "Remove",
                Modifier.clickable { clickStepper.invoke(info.id, RemoveSteppers) })
            Text(
                text = steppers.filterKeys { it == info.id }.values.joinToString(),
                Modifier.width(18.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        Image(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "Add",
            Modifier.clickable { clickStepper.invoke(info.id, AddSteppers) })
    }
}