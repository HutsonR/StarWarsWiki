package com.blackcube.starwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blackcube.starwars.ui.Screen
import com.blackcube.starwars.ui.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(navController = navController)
                }
            }
            //SetStatusBarColor(window)
        }
    }

//    @Composable
//    private fun SetStatusBarColor(window: Window) {
//        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
//        window.statusBarColor = colorResource(id = R.color.currency_change_background).toArgb()
//        windowInsetsController.isAppearanceLightStatusBars = false
//    }
}