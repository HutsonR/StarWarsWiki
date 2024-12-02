package com.blackcube.starwars.ui.home.store.models

import com.blackcube.starwars.ui.common.models.CompositeItem

data class HomeState(
    val lists: List<CompositeItem> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
)