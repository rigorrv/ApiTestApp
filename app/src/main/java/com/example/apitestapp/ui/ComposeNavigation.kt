package com.example.apitestapp.ui

import com.example.apitestapp.utilities.AndroidUtilities

sealed class ComposeNavigation(val route: String) {

    object CollegeList : ComposeNavigation(AndroidUtilities.CollegeList)
    object CollegeInfo : ComposeNavigation(AndroidUtilities.CollegeInfo)
    object Checkout : ComposeNavigation(AndroidUtilities.Checkout)
    object Payment : ComposeNavigation(AndroidUtilities.Payment)
}
