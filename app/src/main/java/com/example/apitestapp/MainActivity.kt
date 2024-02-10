package com.example.apitestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.apitestapp.ui.HomeScreen
import com.example.apitestapp.viewmodel.CollegeSteppersVM
import com.example.apitestapp.viewmodel.CollegeVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val collegeViewModel: CollegeVM by lazy {
        ViewModelProvider(this)[CollegeVM::class.java]
    }

    private val collegeSteppersViewModel: CollegeSteppersVM by lazy {
        ViewModelProvider(this)[CollegeSteppersVM::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val info = collegeViewModel.collegeStateFlow.collectAsState().value
                val steppers = collegeSteppersViewModel.schoolSteppers.collectAsState().value
                if (info.isNullOrEmpty()) {
                    CircularProgressIndicator(
                        Modifier
                            .width(100.dp)
                            .height(100.dp),
                        color = colorResource(id = R.color.red)
                    )
                }
                info?.let {
                    HomeScreen(
                        info,
                        steppers,
                        addSteppers = { content: String?, action: String ->
                            collegeSteppersViewModel.addSteppers(content, action)
                        },
                        addAll = { content, action ->
                            collegeSteppersViewModel.addAllColleges(content, action)
                        }
                    )
                }
            }
        }
    }
}