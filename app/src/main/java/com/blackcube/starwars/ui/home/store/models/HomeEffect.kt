package com.blackcube.starwars.ui.home.store.models

sealed interface HomeEffect {
    data object NavigateToDetails : HomeEffect
    data class ShowToast(val message: String) : HomeEffect
}
