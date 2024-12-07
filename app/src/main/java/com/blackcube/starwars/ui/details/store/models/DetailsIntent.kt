package com.blackcube.starwars.ui.details.store.models

import com.blackcube.starwars.ui.common.models.CompositeItemType

sealed interface DetailsIntent {
    data class OnFavouriteClick(
        val url: String,
        val itemType: CompositeItemType
    ) : DetailsIntent
}