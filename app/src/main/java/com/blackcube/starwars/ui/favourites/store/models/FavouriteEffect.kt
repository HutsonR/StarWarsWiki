package com.blackcube.starwars.ui.favourites.store.models

sealed interface FavouriteEffect {
    data object NavigateToDetails : FavouriteEffect
    data class ShowToast(val message: String) : FavouriteEffect
}
