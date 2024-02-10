package com.example.apitestapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apitestapp.model.ContentDB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Checkout(
    info: ContentDB,
    steppers: MutableList<String>,
    nav: () -> Unit,
    addSteppers: (String?, String) -> Unit,
    getCollegeInfo: (int: Int) -> Unit
) {
    if (steppers.isEmpty()) {
        LaunchedEffect(key1 = 1) {
            nav.invoke()
        }
    }
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        info.toList().let { items ->
            CenterAlignedTopAppBar(title = { Text(text = "Checkout") }, navigationIcon = {
                IconButton(onClick = {
                    nav.invoke()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "BackArrow")
                }
            })
            LazyColumn(content = {
                itemsIndexed(items) { index, item ->
                    if (steppers.contains(item?.dbn)) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 5.dp)
                                .clickable {
                                    getCollegeInfo.invoke(index)
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
                                item = item,
                                steppers = steppers,
                                addSteppers = { content: String?, action: String ->
                                    addSteppers.invoke(content, action)
                                })
                        }
                    }
                }
            })
        }
    }
}