package com.example.apitestapp.ui

sealed class ComposeNavigation(val route: String) {

    object MovieList : ComposeNavigation("MovieList")
    object MovieInfo : ComposeNavigation("MovieInfo")
    object Checkout : ComposeNavigation("Checkout")
    object Payment : ComposeNavigation("Payment")
}