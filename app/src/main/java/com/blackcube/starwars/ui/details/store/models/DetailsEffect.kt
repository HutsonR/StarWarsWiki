package com.blackcube.starwars.ui.details.store.models

sealed interface DetailsEffect {
    data object GoBack : DetailsEffect
    data class ShowToast(val message: String) : DetailsEffect
}
