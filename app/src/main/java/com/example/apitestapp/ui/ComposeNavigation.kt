package com.example.apitestapp.ui

sealed class ComposeNavigation(val rout: String) {

    object MovieList : ComposeNavigation("MovieList")
    object MovieInfo : ComposeNavigation("MovieInfo")
}