package com.example.apitestapp.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apitestapp.R
import com.example.apitestapp.model.content.Content
import com.example.apitestapp.model.content.Result
import com.example.apitestapp.utilities.ApplicationConstants.AddCart
import com.example.apitestapp.utilities.ApplicationConstants.RemoveCart

@Composable
fun SteppersCheckout(
    cart: Result?,
    steppers: MutableMap<Int?, Int>,
    addCart: (content: Content?, result: Result?, action: String) -> Unit
) {
    Row(
        Modifier
            .padding(20.dp)
            .height(30.dp)
            .clip(
                RoundedCornerShape(
                    20.dp,
                )
            )
            .border(BorderStroke(2.dp, Color.Gray), shape = RoundedCornerShape(20.dp))
            .background(
                Color.Black,
                shape = RoundedCornerShape(20.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
            if (steppers.keys.contains(cart?.id)) {
                Image(
                    painter = painterResource(id = R.drawable.remove),
                    contentDescription = stringResource(
                        id = R.string.remove
                    ),
                    Modifier.clickable {
                        addCart.invoke(null, cart, RemoveCart)
                    }
                )
                Text(
                    text = steppers.filterKeys { it == cart?.id }.values.joinToString(),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = stringResource(
                    id = R.string.add
                ),
                Modifier.clickable {
                    addCart.invoke(null, cart, AddCart)
                }
            )
        }
    }
}