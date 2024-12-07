package com.blackcube.starwars.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blackcube.starwars.R
import com.blackcube.starwars.domain.models.PeopleModel
import com.blackcube.starwars.domain.models.StarshipModel
import com.blackcube.starwars.ui.details.store.models.DetailsEffect
import com.blackcube.starwars.ui.details.store.models.DetailsIntent
import com.blackcube.starwars.ui.details.store.models.DetailsState
import kotlinx.coroutines.flow.Flow

@Composable
fun DetailsScreenRoot(
    itemId: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val effects = viewModel.effect

    viewModel.init(itemId)
    DetailsScreen(
        state = state,
        effects = effects,
        onIntent = viewModel::handleIntent
    )
}

@Composable
fun DetailsScreen(
    state: DetailsState,
    effects: Flow<DetailsEffect>,
    onIntent: (DetailsIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF1E1E1E), Color(0xFF121212))
                )
            )
            .padding(16.dp)
    ) {
        when (val item = state.item) {
            is PeopleModel -> {
                SectionHeader(title = "Детали персонажа")
                DisplayPeopleModel(item)
            }
            is StarshipModel -> {
                SectionHeader(title = "Детали звездолёта")
                DisplayStarshipModel(item)
            }
            null -> {
                TextBlank()
            }
        }
    }
}

@Composable
fun DisplayPeopleModel(model: PeopleModel) {
    Column(
        modifier = Modifier
            .background(
                color = Color(0xFF2C2C2C),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        RowTextItem("Имя", model.name)
        DividerStyled()
        RowTextItem("Год рождения", model.birthYear)
        DividerStyled()
        RowTextItem("Пол", model.gender)
        DividerStyled()
        RowTextItem("Рост", model.height)
        DividerStyled()
        RowTextItem("Вес", model.mass)
        DividerStyled()
        RowTextItem("Цвет глаз", model.eyeColor)
        DividerStyled()
        RowTextItem("Цвет волос", model.hairColor)
        DividerStyled()
        RowTextItem("Цвет кожи", model.skinColor)
        DividerStyled()
        RowTextItem("Избранное", if (model.isFavourite) "Да" else "Нет")
    }
}

@Composable
fun DisplayStarshipModel(model: StarshipModel) {
    Column(
        modifier = Modifier
            .background(
                color = Color(0xFF2C2C2C),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        RowTextItem("Название", model.name)
        DividerStyled()
        RowTextItem("Модель", model.model)
        DividerStyled()
        RowTextItem("Производитель", model.manufacturer)
        DividerStyled()
        RowTextItem("Стоимость", model.costInCredits)
        DividerStyled()
        RowTextItem("Длина", model.length)
        DividerStyled()
        RowTextItem("Экипаж", model.crew)
        DividerStyled()
        RowTextItem("Пассажиры", model.passengers)
        DividerStyled()
        RowTextItem("Класс звездолёта", model.starshipClass)
        DividerStyled()
        RowTextItem("Рейтинг гипердвигателя", model.hyperDriveRating)
        DividerStyled()
        RowTextItem("Избранное", if (model.isFavourite) "Да" else "Нет")
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 32.dp,
                bottom = 12.dp
            )
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun RowTextItem(infoField: String, field: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = infoField,
            color = Color(0xFF9397A3),
            fontWeight = Bold,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = field,
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(2f)
        )
    }
}

@Composable
fun DividerStyled() {
    Divider(
        color = Color(0xFF434A54),
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun TextBlank() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Пустовато тут",
            color = Color(0xFF9397A3),
            fontWeight = Bold,
            textAlign = TextAlign.Center,
            fontSize = 21.sp
        )
    }
}
