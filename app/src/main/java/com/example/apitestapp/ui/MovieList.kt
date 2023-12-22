package com.example.apitestapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath
import com.example.apitestapp.utilities.BitmapPreview

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MovieList(
    info: List<Result>,
    cart: MutableMap<Result?, Int>?,
    nav: (int: Int) -> Unit,
    checkoutNav: () -> Unit,
    getMovie: (movie: String?) -> Unit,
    addCart: (Result?, String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val listState = rememberLazyGridState()
    if (listState.isScrollInProgress) {
        keyboardController?.hide()
    }

    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        val textSearch = remember {
            mutableStateOf("")
        }
        Row(
            Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = textSearch.value,
                onValueChange = {
                    textSearch.value = it
                    getMovie.invoke(it)
                },
                Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        RoundedCornerShape(10.dp)
                    )
                    .weight(8f),
                placeholder = { Text(text = "SearchMovie") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                ),
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Filled.AccountCircle, contentDescription = "")
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
            )
            Text(
                text = "Cancel",
                Modifier
                    .weight(2f)
                    .clickable {
                        textSearch.value = ""
                        getMovie.invoke(null)
                    },
                textAlign = TextAlign.Center
            )
        }
        LazyVerticalGrid(
            GridCells.Fixed(2),
            Modifier
                .weight(8f)
                .padding(vertical = 20.dp),
            state = listState,
            content = {
                itemsIndexed(info) { index, item ->
                    BoxWithConstraints {
                        Column(Modifier.clickable {
                            nav.invoke(index)
                        }, horizontalAlignment = Alignment.CenterHorizontally) {
                            BitmapPreview(thumbPath + item.poster_path) //<== MovieDB Images
//                            BitmapPreview(productImage) //Product Remove Background
                            Text(
                                text = item.title,
                                Modifier.padding(20.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Steppers(
                                item,
                                cart,
                            ) { item: Result?, action: String ->
                                addCart.invoke(
                                    item,
                                    action
                                )
                            }
                        }
                    }
                }
            })
        if (!cart.isNullOrEmpty())
            Column(
                Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { checkoutNav.invoke() }
                        .background(color = Color.Black, shape = RoundedCornerShape(20.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        Modifier
                            .padding(start = 20.dp)
                            .weight(1.6f)
                            .width(30.dp)
                            .height(30.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(20.dp)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = cart.values.sum().toString(),
                            textAlign = TextAlign.Center, color = Color.Black
                        )
                    }
                    Text(
                        text = "Checkout",
                        Modifier
                            .weight(8f)
                            .padding(vertical = 20.dp),
                        textAlign = TextAlign.Center, color = Color.White
                    )
                    Box(modifier = Modifier.weight(1.5f))
                }
            }
    }
}