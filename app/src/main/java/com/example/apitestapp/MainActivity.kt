package com.example.apitestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val steppers = viewModel.stepper.collectAsState().value.toList()
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Button(onClick = { viewModel.insertStepper((1..100).random(), "AddStepper") }) {
                    Text(text = "Insert Steppers")
                }
                LazyColumn(content = {
                    itemsIndexed(steppers) { index, item ->
                        Text(text = "${item.first} - ${item.second}")
                    }
                })
            }
        }
    }
}