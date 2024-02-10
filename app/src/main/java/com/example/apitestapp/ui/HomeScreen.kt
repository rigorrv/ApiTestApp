package com.example.apitestapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apitestapp.model.ContentDB

@Composable
fun HomeScreen(
    info: ContentDB,
    steppers: MutableList<String?>,
    addSteppers: (String?, String) -> Unit,
    addAll: (ContentDB, String) -> Unit
) {

    val index = remember {
        mutableStateOf(0)
    }
    Column(
        Modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = ComposeNavigation.CollegeList.route,
            builder = {
                composable(ComposeNavigation.CollegeList.route) {
                    CollegeList(
                        info,
                        steppers,
                        getInfoSchool = { int: Int ->
                            index.value = int
                            navController.navigate(ComposeNavigation.CollegeInfo.route)
                        },
                        addSteppers = { content: String?, action: String ->
                            addSteppers.invoke(
                                content,
                                action
                            )
                        },
                        goCheckout = { navController.navigate(ComposeNavigation.Checkout.route) },
                        addAll = { content, action -> addAll.invoke(content, action) }
                    )
                }
                composable(ComposeNavigation.CollegeInfo.route) {
                    CollegeInfo(
                        info = info[index.value],
                        nav = {
                            navController.popBackStack()
                        },
                        addStepper = { content, action ->
                            addSteppers.invoke(content, action)
                        },
                        steppers = steppers
                    )
                }
                composable(ComposeNavigation.Checkout.route) {
                    Checkout(
                        info,
                        steppers,
                        nav = {
                            navController.popBackStack()
                        },
                        addSteppers = { content, action -> addSteppers.invoke(content, action) },
                        getCollegeInfo = { int: Int ->
                            index.value = int
                            navController.navigate(ComposeNavigation.CollegeInfo.route)
                        },
                        payment = { navController.navigate(ComposeNavigation.Payment.route) }
                    )
                }
                composable(ComposeNavigation.Payment.route) {
                    Payment(
                        info = info,
                        steppers = steppers,
                        nav = {
                            navController.popBackStack()
                        },
                        addSteppers = { content, action -> addSteppers.invoke(content, action) }
                    )
                }
            }
        )
    }
}