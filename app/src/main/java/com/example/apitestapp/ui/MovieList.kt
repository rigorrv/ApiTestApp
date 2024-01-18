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
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.apitestapp.model.cart.Cart
import com.example.apitestapp.model.content.Content
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MovieList(
    info: List<Content>,
    cart: MutableMap<Cart?, Int>,
    steppers: MutableMap<Int?, Int>,
    searchMovie: (String) -> Unit,
    addCart: (content: Content?, cart: Cart?, action: String) -> Unit,
    getMovieInfo: (Int) -> Unit,
    checkout: () -> Unit,
    lastSearch: String,
) {
    val scrollState = rememberLazyGridState()
    val keyBoard = LocalSoftwareKeyboardController.current
    if (scrollState.isScrollInProgress) {
        keyBoard?.hide()
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material.TextField(
                value = lastSearch,
                onValueChange = {
                    searchMovie.invoke(it)
                },
                Modifier
                    .weight(5f)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        RoundedCornerShape(10.dp)
                    ),
                placeholder = { androidx.compose.material.Text(text = stringResource(R.string.search_movie)) },
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
            if (lastSearch.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    Modifier
                        .weight(3f)
                        .clickable {
                            searchMovie.invoke("")
                            keyBoard?.hide()
                        }, textAlign = TextAlign.Center
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            Modifier.weight(8f),
            state = scrollState,
            content = {
                itemsIndexed(info) { index, item ->
                    BoxWithConstraints(contentAlignment = Alignment.TopEnd) {
                        Column(
                            Modifier
                                .padding(10.dp)
                                .clickable {
                                    item.id?.let {
                                        getMovieInfo.invoke(it)
                                    }
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Image(
                                painter = rememberImagePainter(data = thumbPath + item.poster_path),
                                contentDescription = item.title,
                                Modifier
                                    .width(200.dp)
                                    .height(200.dp)
                            )
                            Text(
                                text = item.title.toString(),
                                Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                            )
                            Text(
                                text = item.vote_average.toString(),
                                Modifier
                                    .padding(horizontal = 20.dp)
                                    .fillMaxWidth(),
                            )
                            item.vote_average?.toInt()?.let {
                                RatingBar(
                                    currentRating = it,
                                    modifierRow = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp),
                                    modifierIcon = Modifier.size(20.dp)
                                )
                            }
                        }
                        Steppers(
                            item,
                            steppers = steppers,
                            addCart = { content: Content?, cart: Cart?, action: String ->
                                addCart.invoke(
                                    content,
                                    cart,
                                    action
                                )
                                keyBoard?.hide()
                            }
                        )
                    }
                }
            }
        )
        if (!cart.isNullOrEmpty()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .background(color = Color.Black, RoundedCornerShape(20.dp))
                        .clickable {
                            checkout.invoke()
                        }
                ) {
                    Text(
                        text = stringResource(id = R.string.checkout),
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
    }
}