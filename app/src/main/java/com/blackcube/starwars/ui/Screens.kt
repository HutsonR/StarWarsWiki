package com.blackcube.starwars.ui

sealed class Screens(val route: String) {
    data object MainScreen : Screens("home")
    data object Details : Screens("details")
}