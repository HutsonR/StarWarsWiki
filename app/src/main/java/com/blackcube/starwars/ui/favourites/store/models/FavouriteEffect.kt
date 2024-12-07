package com.blackcube.starwars.ui.favourites.store.models

sealed interface FavouriteEffect {
    data class NavigateToDetails(val id: String) : FavouriteEffect
    data class ShowToast(val message: String) : FavouriteEffect
}
