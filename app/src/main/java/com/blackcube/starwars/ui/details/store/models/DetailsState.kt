package com.blackcube.starwars.ui.details.store.models

import com.blackcube.starwars.domain.models.DomainModel

data class DetailsState(
    val item: DomainModel? = null,
    val isLoading: Boolean = false
)