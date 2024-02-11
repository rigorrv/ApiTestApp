package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.apitestapp.R
import com.example.apitestapp.model.ContentDBItem
import com.example.apitestapp.utilities.AndroidUtilities.AddAll
import com.example.apitestapp.utilities.AndroidUtilities.RemoveAll

@Composable
fun AddMultipleSteppers(
    info: List<ContentDBItem?>,
    stepper: MutableList<String?>,
    addAll: (List<ContentDBItem?>, String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(start = 10.dp)
            .border(
                width = 1.dp,
                color =
                if (stepper.isNullOrEmpty()) {
                    Color.Black
                } else {
                    colorResource(id = R.color.red)
                },
                RoundedCornerShape(5.dp)
            )
    ) {
        Image(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(R.string.checkout_button),
            Modifier
                .clickable {
                    if (stepper.isNullOrEmpty()) {
                        addAll.invoke(info, AddAll)
                    } else {
                        addAll.invoke(info, RemoveAll)
                    }
                },
            colorFilter = ColorFilter.tint(
                if (stepper.isNullOrEmpty()) {
                    colorResource(id = R.color.lowGray)
                } else {
                    colorResource(id = R.color.red)
                }
            )
        )
    }
}
