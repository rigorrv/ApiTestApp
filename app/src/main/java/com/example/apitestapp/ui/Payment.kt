package com.example.apitestapp.ui

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
fun Payment(
    info: ContentDB,
    steppers: MutableList<String>,
    nav: () -> Unit,
    addSteppers: (String?, String) -> Unit
) {
    if (steppers.isEmpty()) {
        LaunchedEffect(key1 = 1) {
            nav.invoke()
        }
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(title = { Text(text = "Payment") }, navigationIcon = {
            IconButton(onClick = {
                addSteppers.invoke(null, "Delete")
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        })
        info.toList().let { items ->
            LazyColumn(content = {
                itemsIndexed(items) { index, item ->
                    if (steppers.contains(item?.dbn)) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 5.dp),
                            verticalAlignment = CenterVertically
                        ) {
                            Text(text = item?.school_name.toString(), Modifier.weight(5f))
                            Text(
                                text = item?.dbn.toString(),
                                Modifier.weight(5f),
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            })
        }
    }
}