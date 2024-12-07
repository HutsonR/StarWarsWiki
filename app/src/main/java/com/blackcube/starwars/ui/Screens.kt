package com.blackcube.starwars.ui

import android.net.Uri

sealed class Screens(val route: String) {
    data object MainScreen : Screens("home")
    data object Details : Screens("details") {
        fun createRoute(id: String) = "details/${Uri.encode(id)}"
    }
}