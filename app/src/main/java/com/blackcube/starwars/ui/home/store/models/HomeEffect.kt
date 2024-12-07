package com.blackcube.starwars.ui.home.store.models

sealed interface HomeEffect {
    data class NavigateToDetails(val id: String) : HomeEffect
    data class ShowToast(val message: String) : HomeEffect
}
