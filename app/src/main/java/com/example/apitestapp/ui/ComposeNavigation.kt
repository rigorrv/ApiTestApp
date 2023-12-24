package com.example.apitestapp.ui

import com.example.apitestapp.utilities.ApplicationConstants.Checkout
import com.example.apitestapp.utilities.ApplicationConstants.MovieInfo
import com.example.apitestapp.utilities.ApplicationConstants.MovieList
import com.example.apitestapp.utilities.ApplicationConstants.Payment

sealed class ComposeNavigation(val route: String) {

    object MovieListNav : ComposeNavigation(MovieList)
    object MovieInfoNav : ComposeNavigation(MovieInfo)
    object CheckoutNav : ComposeNavigation(Checkout)
    object PaymentNav : ComposeNavigation(Payment)
}
