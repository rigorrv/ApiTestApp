package com.example.apitestapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apitestapp.R
import com.example.apitestapp.model.ContentDBItem
import com.example.apitestapp.utilities.AndroidUtilities.AddSteppers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollegeInfo(
    info: ContentDBItem?,
    steppers: MutableList<String?>,
    nav: () -> Unit,
    addStepper: (String, String) -> Unit
) {
    val scroll = rememberScrollState()
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(R.string.college_info)) },
            navigationIcon = {
                IconButton(onClick = {
                    nav.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_buton)
                    )
                }
            })
        Text(
            text = info?.school_name.toString(),
            Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight(900)
        )
        Column(
            Modifier
                .verticalScroll(
                    scroll
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = info?.overview_paragraph.toString(),
                Modifier.padding(20.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = info?.dbn.toString(),
                Modifier.padding(20.dp),
                textAlign = TextAlign.Center
            )
            if (!steppers.contains(info?.dbn)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clickable {
                            addStepper.invoke(info?.dbn.toString(), AddSteppers)
                        }
                        .background(Color.Black, RoundedCornerShape(20.dp))
                ) {
                    Text(
                        text = stringResource(R.string.add_college),
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            } else {
                Text(
                    text = stringResource(R.string.add_to_basket),
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight(900)
                )
            }
        }
    }
}