package com.blackcube.starwars.ui.favourites

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Airlines
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.blackcube.starwars.R
import com.blackcube.starwars.ui.Screens
import com.blackcube.starwars.ui.common.models.CompositeItem
import com.blackcube.starwars.ui.common.components.SearchBar
import com.blackcube.starwars.ui.common.components.TextSwitch
import com.blackcube.starwars.ui.common.components.loader.AtomicLoader
import com.blackcube.starwars.ui.common.models.CompositeItemType
import com.blackcube.starwars.ui.home.store.models.HomeEffect
import com.blackcube.starwars.ui.home.store.models.HomeIntent
import com.blackcube.starwars.ui.home.store.models.HomeState
import com.blackcube.starwars.ui.common.utils.CollectEffect
import com.blackcube.starwars.ui.common.utils.CollectUpdater
import com.blackcube.starwars.ui.common.utils.UpdateNotifier
import com.blackcube.starwars.ui.favourites.store.models.FavouriteEffect
import com.blackcube.starwars.ui.favourites.store.models.FavouriteIntent
import com.blackcube.starwars.ui.favourites.store.models.FavouriteState
import kotlinx.coroutines.flow.Flow

@Composable
fun FavouriteScreenRoot(
    navController: NavController,
    viewModel: FavouriteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val effects = viewModel.effect

    FavouriteScreen(
        navController = navController,
        state = state,
        effects = effects,
        onIntent = viewModel::handleIntent
    )
}

@Composable
fun FavouriteScreen(
    navController: NavController,
    state: FavouriteState,
    effects: Flow<FavouriteEffect>,
    onIntent: (FavouriteIntent) -> Unit
) {
    val context = LocalContext.current

    CollectUpdater(
        updateFlow = UpdateNotifier.updateFlow,
        resetEvent = UpdateNotifier.UpdateEvent.None
    ) { event ->
        if (event is UpdateNotifier.UpdateEvent.FromHome) {
            UpdateNotifier.resetUpdate()
            onIntent(FavouriteIntent.UpdateList)
        }
    }

    CollectEffect(effects) { effect ->
        when (effect) {
            is FavouriteEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }

            is FavouriteEffect.NavigateToDetails -> {
                navController.navigate(Screens.Details.createRoute(effect.id))
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.main_background))
    ) {

        Box(modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)) {
            SearchBar(
                contentModifier = Modifier
                    .background(Color(0xFF111214))
                    .height(48.dp)
                ,
                value = state.searchQuery,
                onValueChange = { query ->
                    onIntent(FavouriteIntent.SearchQueryChanged(query))
                },
                onSearch = { }
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (state.isLoading) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        AtomicLoader(
                            Modifier.size(60.dp)
                        )
                    }
                }
            } else {
                itemsIndexed(state.lists, key = { _, item -> item.url }) { index, item ->
                    when(item) {
                        is CompositeItem.PeopleItem -> {
                            PeopleItem(
                                name = item.name,
                                gender = item.gender,
                                starshipsCount = item.starshipsCount,
                                date = item.date,
                                isFavourite = item.isFavourite,
                                onItemClick = { onIntent(FavouriteIntent.OnItemClick(item.url)) },
                                onFavouriteClick = { onIntent(FavouriteIntent.OnFavouriteClick(item.url, CompositeItemType.People)) }
                            )
                        }
                        is CompositeItem.StarshipItem -> {
                            StarshipItem(
                                name = item.name,
                                model = item.model,
                                passengers = item.passengers,
                                pilots = item.pilots,
                                isFavourite = item.isFavourite,
                                onItemClick = { onIntent(FavouriteIntent.OnItemClick(item.url)) },
                                onFavouriteClick = { onIntent(FavouriteIntent.OnFavouriteClick(item.url, CompositeItemType.Starship)) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PeopleItem(
    name: String,
    gender: String,
    starshipsCount: String,
    date: String,
    isFavourite: Boolean,
    onItemClick: () -> Unit,
    onFavouriteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF111214))
            .padding(all = 16.dp)
    ) {
        // Колонка для текстовых элементов
        Column(
            modifier = Modifier.clickable { onItemClick.invoke() }
        ) {
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = name,
                color = Color.White,
                fontWeight = Bold,
                fontSize = 18.sp
            )
            RowTextItem("пол", gender)
            RowTextItem(
                "кол-во управляемых звездолётов",
                starshipsCount,
                Modifier.padding(top = 8.dp)
            )
            RowTextItem("дата рождения", date, Modifier.padding(top = 8.dp))
        }

        AnimatedFavoriteButton(
            modifier = Modifier
                .align(Alignment.TopEnd),
            isFavourite
        ) { onFavouriteClick.invoke() }

        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "People",
            tint = Color(0xFF212224),
            modifier = Modifier
                .size(86.dp)
                .align(Alignment.BottomEnd)
                .offset(
                    y = 32.dp,
                    x = 34.dp
                )
        )
    }
}

@Composable
private fun StarshipItem(
    name: String,
    model: String,
    passengers: String,
    pilots: String,
    isFavourite: Boolean,
    onItemClick: () -> Unit,
    onFavouriteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF111214))
            .padding(all = 16.dp)
    ) {
        // Колонка для текстовых элементов
        Column(
            modifier = Modifier.clickable { onItemClick.invoke() }
        ) {
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = name,
                color = Color.White,
                fontWeight = Bold,
                fontSize = 18.sp
            )
            RowTextItem("модель", model)
            RowTextItem(
                "кол-во пассажиров",
                passengers,
                Modifier.padding(top = 8.dp)
            )
            RowTextItem("кол-во пилотов", pilots, Modifier.padding(top = 8.dp))
        }

        AnimatedFavoriteButton(
            modifier = Modifier
                .align(Alignment.TopEnd),
            isFavourite
        ) { onFavouriteClick.invoke() }

        Icon(
            imageVector = Icons.Filled.Airlines,
            contentDescription = "Airlines",
            tint = Color(0xFF212224),
            modifier = Modifier
                .size(86.dp)
                .align(Alignment.BottomEnd)
                .offset(
                    y = 32.dp,
                    x = 34.dp
                )
        )
    }
}

@Composable
fun AnimatedFavoriteButton(
    modifier: Modifier,
    isFavourite: Boolean,
    onFavouriteClick: () -> Unit
) {
    // Анимация цвета
    val tint by animateColorAsState(
        targetValue = if (isFavourite) Color(0xFFF44336) else Color(0xFF9397A3),
        animationSpec = tween(durationMillis = 300), label = ""
    )

    // Анимация размера
    val scale by animateFloatAsState(
        targetValue = if (isFavourite) 1.2f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMedium), label = ""
    )

    Box(modifier = modifier) {
        IconButton(
            onClick = { onFavouriteClick.invoke() },
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Bookmark,
                contentDescription = "Favorite Icon",
                tint = tint,
                modifier = Modifier
                    .size(28.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    )
            )
        }
    }
}


@Composable
private fun RowTextItem(infoField: String, field: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = "$infoField:",
            color = Color(0xFF9397A3),
            fontWeight = Bold,
            fontSize = 14.sp
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = field,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun TextSwitchTest() {
    val items = remember {
        listOf("Home", "Favorite")
    }
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column {
        TextSwitch(
            selectedIndex = selectedIndex,
            items = items,
            onSelectionChange = {
                selectedIndex = it
            }
        )
    }
}

@Preview
@Composable
private fun ShowPeopleItem() {
    PeopleItem(
        "Роман Тузов",
        "мужской",
        "21",
        "21.10.2003",
        false,
        {},
        {}
    )
}

@Preview
@Composable
private fun ShowStarshipItem() {
    StarshipItem(
        "ХЫХЫХЫ",
        "Машина",
        "8",
        "1",
        true,
        {},
        {}
    )
}