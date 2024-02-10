package com.example.apitestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apitestapp.R
import com.example.apitestapp.model.ContentDB
import com.example.apitestapp.utilities.AndroidUtilities.Delete

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Checkout(
    info: ContentDB,
    steppers: MutableList<String?>,
    nav: () -> Unit,
    addSteppers: (String?, String) -> Unit,
    getCollegeInfo: (int: Int) -> Unit,
    payment: () -> Unit
) {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        info.toList().let { items ->
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.checkout)) },
                navigationIcon = {
                    IconButton(onClick = {
                        nav.invoke()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.back_buton
                            )
                        )
                    }
                })
            if (steppers.isNullOrEmpty()) {
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
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    verticalAlignment = CenterVertically
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
                    Image(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.delet),
                        Modifier
                            .padding(start = 10.dp)
                            .clickable {
                                addSteppers.invoke(null, Delete)
                            },
                    )
                }
                LazyColumn(
                    Modifier.weight(8f),
                    content = {
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
                Column(
                    Modifier
                        .weight(2f)
                        .padding(20.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                colorResource(id = R.color.red), RoundedCornerShape(20.dp)
                            )
                            .clickable {
                                payment.invoke()
                            }
                    ) {
                        Text(
                            text = stringResource(id = R.string.payment),
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