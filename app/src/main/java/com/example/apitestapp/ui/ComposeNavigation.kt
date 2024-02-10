package com.example.apitestapp.ui

sealed class ComposeNavigation(val route: String) {

    object CollegeList : ComposeNavigation("CollegeList")
    object CollegeInfo : ComposeNavigation("CollegeInfo")
    object Checkout : ComposeNavigation("Checkout")
    object Payment : ComposeNavigation("Payment")
}
