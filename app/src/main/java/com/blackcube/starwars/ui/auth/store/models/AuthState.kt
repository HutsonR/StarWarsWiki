package com.blackcube.starwars.ui.auth.store.models

data class AuthState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)