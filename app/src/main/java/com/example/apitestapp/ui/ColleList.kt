package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apitestapp.R
import com.example.apitestapp.model.ContentDB
import com.example.apitestapp.model.ContentDBItem
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CollegeList(
    info: ContentDB,
    steppers: MutableList<String?>,
    getInfoSchool: (int: Int) -> Unit,
    addSteppers: (String?, String) -> Unit,
    addAll: (List<ContentDBItem?>, String) -> Unit,
    goCheckout: () -> Unit
) {
    val scroll = rememberLazyListState()
    val keyBoard = LocalSoftwareKeyboardController.current
    if (scroll.isScrollInProgress) {
        keyBoard?.hide()
    }
    val search = remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.asu),
            contentDescription = stringResource(R.string.logo),
            Modifier.padding(30.dp)
        )
        info.filter {
            it?.school_name.toString().lowercase(Locale.getDefault()).contains(search.value)
        }.let { infos ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = CenterVertically
            ) {
                TextField(
                    value = search.value,
                    onValueChange = {
                        search.value = it
                    },
                    Modifier
                        .weight(8f)
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.red),
                            RoundedCornerShape(10.dp)
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search_college),
                            color = colorResource(id = R.color.red),
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                )
                if (search.value.isNotEmpty()) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        Modifier
                            .padding(start = 12.dp)
                            .weight(2f)
                            .clickable {
                                keyBoard?.hide()
                                search.value = ""
                            },
                        textAlign = TextAlign.End
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalAlignment = CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.college_name), Modifier.weight(5f),
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = stringResource(R.string.dbn_tx),
                    Modifier
                        .weight(5f)
                        .padding(end = 10.dp),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight(500)
                )
                AddMultipleSteppers(
                    infos,
                    steppers
                ) { content, action ->
                    keyBoard?.hide()
                    addAll.invoke(content, action)
                }
            }
            if (infos.isNullOrEmpty()) {
                Text(
                    text = stringResource(R.string.no_result),
                    Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.red),
                    textAlign = TextAlign.Center
                )
            }
            LazyColumn(
                Modifier.weight(8f),
                state = scroll,
                content = {
                    itemsIndexed(infos) { index, item ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 5.dp)
                                .clickable {
                                    getInfoSchool.invoke(index)
                                },
                            verticalAlignment = CenterVertically
                        ) {
                            Text(text = item?.school_name.toString(), Modifier.weight(5f))
                            Text(
                                text = item?.dbn.toString(),
                                Modifier
                                    .weight(5f)
                                    .padding(end = 10.dp),
                                textAlign = TextAlign.End
                            )
                            Steppers(
                                item,
                                steppers
                            ) { content: String?, action: String ->
                                keyBoard?.hide()
                                addSteppers.invoke(content, action)
                            }
                        }
                    }
                })
            if (steppers.isNotEmpty()) {
                Column(
                    Modifier
                        .weight(2f)
                        .padding(20.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                colorResource(id = R.color.red),
                                RoundedCornerShape(20.dp)
                            )
                            .clickable {
                                goCheckout.invoke()
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
}