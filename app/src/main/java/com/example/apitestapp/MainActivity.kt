package com.example.apitestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.example.apitestapp.ui.HomeScreen
import com.example.apitestapp.viewmodel.CollegeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Please Take a look to the Branch photon_test_room
     * Where I added Room and steppers to this project
     * to show how to developed the architecture of an e-commerce with compose
     * MVVM, Dagger Hilt, Room, Filtering a list through a TextField
     * thank you
     */

    private val collegeViewModel: CollegeViewModel by lazy {
        ViewModelProvider(this)[CollegeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val info = collegeViewModel.collegeStateFlow.collectAsState().value
            info?.let {
                HomeScreen(info)
            }
        }
    }
}