package com.example.apitestapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apitestapp.model.ContentDBItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollegeInfo(contentDBItem: ContentDBItem?, nav: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(title = { Text(text = "College Info") }, navigationIcon = {
            IconButton(onClick = {
                nav.invoke()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "BackArrow")
            }
        })
        Text(text = contentDBItem?.school_name.toString(), Modifier.padding(20.dp), textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight(900))
        Text(text = contentDBItem?.overview_paragraph.toString(), Modifier.padding(20.dp), textAlign = TextAlign.Center)
        Text(text = contentDBItem?.dbn.toString(), Modifier.padding(20.dp), textAlign = TextAlign.Center)
    }
}