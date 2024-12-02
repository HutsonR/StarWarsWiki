package com.blackcube.starwars

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blackcube.starwars.ui.Screens
import com.blackcube.starwars.ui.common.MainScreen
import com.blackcube.starwars.ui.details.DetailsScreen
import com.blackcube.starwars.ui.details.DetailsScreenRoot
import com.blackcube.starwars.ui.home.HomeScreenRoot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screens.MainScreen.route
            ) {
                composable(Screens.MainScreen.route) {
                    MainScreen(navController = navController)
                }
                composable(Screens.Details.route) {
                    DetailsScreenRoot(navController = navController)
                }
            }
            SetStatusBarColor(window)
        }
    }

    @Composable
    private fun SetStatusBarColor(window: Window) {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = false
    }
}