package com.blackcube.starwars.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blackcube.starwars.R
import com.blackcube.starwars.ui.home.HomeScreenRoot
import com.blackcube.starwars.ui.common.components.TextSwitch
import com.blackcube.starwars.ui.favourites.FavouriteScreenRoot
import com.blackcube.starwars.ui.home.HomeViewModel

@Composable
fun MainScreen(
    navController: NavController
) {
    val items = remember { listOf("Главная", "Избранное") }
    var tabIndex by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier
            .background(colorResource(id = R.color.main_background))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.main_background))
        ) {
            Header()
            Column(
                modifier = Modifier.padding(top = 12.dp)
            ) {
                TextSwitch(
                    selectedIndex = tabIndex,
                    items = items,
                    onSelectionChange = {
                        tabIndex = it
                    }
                )

                when(tabIndex) {
                    0 -> HomeScreenRoot(navController)
                    1 -> FavouriteScreenRoot()
                }
            }
        }
    }
}

@Composable
fun Header() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.futuristic_back),
            contentDescription = "Futuristic background",
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .blur(14.dp)
                .alpha(0.4f),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxWidth(),
            text = "StarWars Wiki",
            color = Color.White,
            fontSize = 21.sp,
            fontWeight = Black,
            textAlign = TextAlign.Center
        )
    }
}