package com.blackcube.starwars.ui.favourites.store.models

import com.blackcube.starwars.ui.common.models.CompositeItemType

sealed interface FavouriteIntent {
    data object UpdateList : FavouriteIntent

    data class OnFavouriteClick(
        val url: String,
        val itemType: CompositeItemType
    ) : FavouriteIntent

    data class SearchQueryChanged(
        val query: String
    ) : FavouriteIntent
}