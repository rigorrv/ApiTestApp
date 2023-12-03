package com.example.apitestapp.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.apitestapp.model.Result
import com.example.apitestapp.utilities.ApplicationConstants.Delet
import com.example.apitestapp.utilities.ApplicationConstants.thumbPath

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Checkout(
    info: List<Result?>?,
    steppers: MutableMap<Int, Int>,
    navBack: () -> Unit,
    click: (int: Int) -> Unit,
    stepperClick: (id: Int?, action: String) -> Unit,
    backHome: () -> Unit,
    payNav: () -> Unit
) {
    if (steppers.isNullOrEmpty()) {
        LaunchedEffect(key1 = Unit) {
            backHome.invoke()
        }
    }
    info?.let {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp), horizontalAlignment = Start
            ) {
                Image(
                    imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                    Modifier
                        .clickable { navBack.invoke() }
                )
            }
            Column {
                LazyColumn(
                    Modifier.weight(8f),
                    content = {
                        itemsIndexed(info) { index, item ->
                            if (steppers.containsKey(item?.id))
                                Row(
                                    Modifier.padding(start = 20.dp, end = 20.dp, bottom = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = rememberImagePainter(data = thumbPath + item?.poster_path),
                                        contentDescription = item?.title,
                                        Modifier
                                            .weight(2f)
                                            .width(100.dp)
                                            .height(100.dp)
                                            .clickable { click.invoke(index) }
                                    )
                                    Text(
                                        text = item?.title.toString(),
                                        Modifier
                                            .weight(7f)
                                            .padding(start = 20.dp)
                                    )
                                    Steppers(
                                        info = item,
                                        steppers = steppers,
                                        click = { id, action -> stepperClick.invoke(id, action) })
                                    Image(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delet",
                                        Modifier
                                            .padding(start = 20.dp)
                                            .clickable {
                                                stepperClick.invoke(item?.id, Delet)
                                            }
                                    )
                                }
                        }
                    })
                Column(
                    Modifier
                        .weight(1.5f)
                        .padding(20.dp)
                ) {
                    CTA(stepper = steppers) {
                        payNav.invoke()
                        Log.d("TAG", "Checkout: Check")
                    }
                }
            }
        }
    }
}