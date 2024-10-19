package com.blackcube.starwars.ui

sealed class Screen(val route: String) {
    data object Home : Screen("home")
}