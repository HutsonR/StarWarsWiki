package com.blackcube.starwars.ui.home.store.models

import com.blackcube.starwars.ui.common.models.CompositeItemType

sealed interface HomeIntent {
    data class OnFavouriteClick(
        val url: String,
        val itemType: CompositeItemType
    ) : HomeIntent

    data class SearchQueryChanged(
        val query: String
    ) : HomeIntent

    data class OnItemClick(
        val itemId: String
    ) : HomeIntent

    data object UpdateList : HomeIntent
}