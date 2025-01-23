package com.blackcube.starwars.ui.auth.store.models

sealed interface AuthEffect {
    data object NavigateToMain : AuthEffect
    data class ShowToast(val message: String) : AuthEffect
}
