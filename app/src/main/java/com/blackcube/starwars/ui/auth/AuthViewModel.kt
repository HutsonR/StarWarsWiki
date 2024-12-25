package com.blackcube.starwars.ui.auth

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.blackcube.starwars.core.BaseViewModel
import com.blackcube.starwars.domain.models.User
import com.blackcube.starwars.domain.usecases.api.PeopleUseCase
import com.blackcube.starwars.domain.usecases.api.StarshipsUseCase
import com.blackcube.starwars.domain.usecases.api.UserUseCase
import com.blackcube.starwars.ui.auth.store.models.AuthEffect
import com.blackcube.starwars.ui.auth.store.models.AuthIntent
import com.blackcube.starwars.ui.auth.store.models.AuthState
import com.blackcube.starwars.ui.common.models.CompositeItem
import com.blackcube.starwars.ui.common.models.toCompositeItem
import com.blackcube.starwars.ui.common.models.CompositeItemType
import com.blackcube.starwars.ui.common.utils.UpdateNotifier
import com.blackcube.starwars.ui.details.store.models.DetailsEffect
import com.blackcube.starwars.ui.favourites.store.models.FavouriteEffect
import com.blackcube.starwars.ui.favourites.store.models.FavouriteIntent
import com.blackcube.starwars.ui.favourites.store.models.FavouriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : BaseViewModel<AuthState, AuthEffect>(AuthState()) {

    init {
        Log.d("debugTag", "GGG")
    }

    private fun registerUser(user: User) {
        viewModelScope.launch {
            try {
                if (userUseCase.registerUser(user)) {
                    effect(AuthEffect.NavigateToMain)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                effect(AuthEffect.ShowToast("Регистрация неуспешна"))
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                val loggedInUser = userUseCase.loginUser(username, password)
                if (loggedInUser != null) {
                    effect(AuthEffect.NavigateToMain)
                } else {
                    effect(AuthEffect.ShowToast("Неверный логин или пароль"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                effect(AuthEffect.ShowToast("Ошибка авторизации"))
            }
        }
    }

    fun handleIntent(authIntent: AuthIntent) {
        when(authIntent) {
            is AuthIntent.UsernameChanged -> {
                modifyState {
                    copy(username = authIntent.username)
                }
            }

            is AuthIntent.PasswordChanged -> {
                modifyState {
                    copy(password = authIntent.password)
                }
            }

            AuthIntent.Login -> {
                val login = getState().username
                val password = getState().password
                if (login.isNotEmpty() && password.isNotEmpty()) {
                    loginUser(login, password)
                }
            }

            AuthIntent.Register -> {
                val login = getState().username
                val password = getState().password
                if (login.isNotEmpty() && password.isNotEmpty()) {
                    registerUser(
                        User(
                            username = login,
                            password = password
                        )
                    )
                }
            }
        }
    }
}