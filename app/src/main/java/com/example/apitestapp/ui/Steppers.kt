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
import com.example.apitestapp.model.content.Result
import com.example.apitestapp.utilities.ApplicationConstants.AddCart
import com.example.apitestapp.utilities.ApplicationConstants.RemoveCart

@Composable
fun Steppers(
    item: Result?,
    cart: MutableMap<Result?, Int>,
    addCart: (movie: Result?, action: String) -> Unit
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
            if (cart.keys.contains(item)) {
                Image(painter = painterResource(id = R.drawable.remove),
                    contentDescription = stringResource(
                        id = R.string.remove
                    ),
                    Modifier.clickable { addCart.invoke(item, RemoveCart) })
                Text(
                    text = cart.filterKeys { it == item }.values.joinToString(),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Image(painter = painterResource(id = R.drawable.add),
                contentDescription = stringResource(
                    id = R.string.add
                ),
                Modifier.clickable { addCart.invoke(item, AddCart) })
        }
    }
}
