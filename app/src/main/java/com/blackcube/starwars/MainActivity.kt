package com.blackcube.starwars

import android.net.Uri
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.blackcube.starwars.ui.Screens
import com.blackcube.starwars.ui.common.MainScreen
import com.blackcube.starwars.ui.details.DetailsScreenRoot
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
                composable(
                    route = Screens.Details.route + "/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })) { stackEntry ->
                    val itemId = Uri.decode(stackEntry.arguments?.getString("id"))
                    DetailsScreenRoot(itemId ?: "")
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