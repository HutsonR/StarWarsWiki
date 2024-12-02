package com.blackcube.starwars.ui.favourites.store.models

import com.blackcube.starwars.ui.common.models.CompositeItem

data class FavouriteState(
    val lists: List<CompositeItem> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
)