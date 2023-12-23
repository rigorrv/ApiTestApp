package com.example.apitestapp.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.apitestapp.R
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MovieList(
    info: List<Result>,
    cart: MutableMap<Result?, Int>,
    addCart: (Result, String) -> Unit,
    searchMovie: (String) -> Unit,
    nav: (Int) -> Unit,
    payment: () -> Unit
) {
    val scrollState = rememberLazyGridState()
    val keyboard = LocalSoftwareKeyboardController.current
    if (scrollState.isScrollInProgress) {
        keyboard?.hide()
    }
    val search = remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.weight(8f)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = search.value,
                    onValueChange = {
                        search.value = it
                        searchMovie.invoke(it)
                    },
                    Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            RoundedCornerShape(10.dp)
                        )
                        .weight(8f),
                    placeholder = { Text(text = stringResource(R.string.search_movie)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = stringResource(R.string.search_icon)
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                Text(
                    text = stringResource(id = R.string.cancel),
                    Modifier
                        .weight(2f)
                        .clickable {
                            search.value = ""
                            searchMovie.invoke("")
                        },
                    textAlign = TextAlign.Center
                )
            }
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                state = scrollState,
                content = {
                    itemsIndexed(info) { index, item ->
                        BoxWithConstraints(contentAlignment = Alignment.TopEnd) {
                            Column(
                                Modifier
                                    .clickable { nav.invoke(index) },
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = thumbPath + item.poster_path),
                                    contentDescription = item.title,
                                    Modifier
                                        .width(200.dp)
                                        .height(200.dp)
                                        .padding(10.dp)
                                )
                                Text(text = item.title.toString(), textAlign = TextAlign.Center)
                            }
                            Steppers(item, cart) { movie: Result, action: String ->
                                addCart.invoke(
                                    movie,
                                    action
                                )
                            }
                        }
                    }
                })
        }
        if (!cart.isNullOrEmpty()) {
            Column(Modifier.weight(1f)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
                        .clickable { payment.invoke() }
                ) {
                    Text(
                        text = stringResource(id = R.string.checkout),
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}