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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apitestapp.R
import com.example.apitestapp.model.ContentDB
import com.example.apitestapp.utilities.AndroidUtilities.Delete

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Payment(
    info: ContentDB,
    steppers: MutableList<String?>,
    nav: () -> Unit,
    addSteppers: (String?, String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(title = {
            Text(
                text = stringResource(R.string.payment),
            )
        }, navigationIcon = {
            IconButton(onClick = {
                addSteppers.invoke(null, Delete)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_buton)
                )
            }
        })
        if (steppers.isEmpty()) {
            Text(
                text = stringResource(R.string.removing_items),
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                textAlign = TextAlign.Center
            )
            LaunchedEffect(key1 = 1) {
                nav.invoke()
            }
        } else {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.college_name),
                    Modifier.weight(5f),
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = stringResource(R.string.dbn_tx),
                    Modifier.weight(5f),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight(500)
                )
            }
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
}