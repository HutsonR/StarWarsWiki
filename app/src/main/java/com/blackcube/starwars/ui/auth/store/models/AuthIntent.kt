package com.blackcube.starwars.ui.auth.store.models

sealed interface AuthIntent {
    data class UsernameChanged(val username: String) : AuthIntent
    data class PasswordChanged(val password: String) : AuthIntent
    data object Register : AuthIntent
    data object Login : AuthIntent
}