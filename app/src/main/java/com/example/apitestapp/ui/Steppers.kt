package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apitestapp.R
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.AddCart
import com.example.apitestapp.utilities.ApplicationConstants.RemoveCart

@Composable
fun Steppers(
    item: Result,
    cart: MutableMap<Result, Int>?,
    addCart: (Result, String) -> Unit
) {
    Row(
        Modifier.background(
            color = Color.Black,
            shape = RoundedCornerShape(20.dp)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (cart?.contains(item) == true) {
            Image(
                painter = painterResource(id = R.drawable.remove),
                contentDescription = "Remove",
                Modifier.clickable {
                    addCart.invoke(item, RemoveCart)
                }
            )
            Text(
                text = cart.filterKeys { it.id == item.id }.values.joinToString(),
                Modifier.width(18.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        Image(painter = painterResource(id = R.drawable.add), contentDescription = "Add",
            Modifier.clickable {
                addCart.invoke(item, AddCart)
            }
        )
    }
}
