package com.example.apitestapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.apitestapp.model.ContentDB

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CollegeList(
    info: ContentDB,
    getInfoSchool: (int: Int) -> Unit
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
        info.toList().let { infos ->
            TextField(value = search.value, onValueChange = { search.value = it })
            LazyColumn(
                state = scroll,
                content = {
                    itemsIndexed(infos) { index, item ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 5.dp)
                                .clickable {
                                    getInfoSchool.invoke(index)
                                }
                        ) {
                            Text(text = item?.school_name.toString(), Modifier.weight(5f))
                            Text(text = item?.dbn.toString(), Modifier.weight(5f))
                        }
                    }
                })
        }
    }
}