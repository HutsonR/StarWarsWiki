package com.blackcube.starwars.ui.auth

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Airlines
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.blackcube.starwars.R
import com.blackcube.starwars.ui.Screens
import com.blackcube.starwars.ui.auth.store.models.AuthEffect
import com.blackcube.starwars.ui.auth.store.models.AuthIntent
import com.blackcube.starwars.ui.auth.store.models.AuthState
import com.blackcube.starwars.ui.common.models.CompositeItem
import com.blackcube.starwars.ui.common.components.SearchBar
import com.blackcube.starwars.ui.common.components.TextSwitch
import com.blackcube.starwars.ui.common.components.loader.AtomicLoader
import com.blackcube.starwars.ui.common.models.CompositeItemType
import com.blackcube.starwars.ui.common.utils.CollectEffect
import com.blackcube.starwars.ui.common.utils.CollectUpdater
import com.blackcube.starwars.ui.common.utils.UpdateNotifier
import com.blackcube.starwars.ui.favourites.store.models.FavouriteEffect
import com.blackcube.starwars.ui.favourites.store.models.FavouriteIntent
import com.blackcube.starwars.ui.favourites.store.models.FavouriteState
import kotlinx.coroutines.flow.Flow

@Composable
fun AuthScreenRoot(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val effects = viewModel.effect

    AuthScreen(
        navController = navController,
        state = state,
        effects = effects,
        onIntent = viewModel::handleIntent
    )
}

@Composable
fun AuthScreen(
    navController: NavController,
    state: AuthState,
    effects: Flow<AuthEffect>,
    onIntent: (AuthIntent) -> Unit
) {
    val context = LocalContext.current

    CollectEffect(effects) { effect ->
        when (effect) {
            is AuthEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }

            AuthEffect.NavigateToMain -> {
                navController.navigate(Screens.MainScreen.route)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.main_background))
            .padding(
                vertical = 52.dp,
                horizontal = 16.dp
            )
    ) {
        // Заголовок
        Text(
            text = "Добро пожаловать",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Поле для логина
        TextField(
            value = state.username,
            onValueChange = { onIntent(AuthIntent.UsernameChanged(it)) },
            label = { Text("Логин") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Поле для пароля
        TextField(
            value = state.password,
            onValueChange = { onIntent(AuthIntent.PasswordChanged(it)) },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Кнопка для регистрации
        Button(
            onClick = { onIntent(AuthIntent.Register) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Зарегистрироваться")
        }

        // Кнопка для авторизации
        Button(
            onClick = { onIntent(AuthIntent.Login) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Войти")
        }

        // Состояние загрузки (если необходимо)
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }

}