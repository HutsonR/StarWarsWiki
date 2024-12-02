package com.blackcube.starwars.ui.common.models

sealed interface CompositeItemType {
   data object Starship : CompositeItemType
   data object People : CompositeItemType
}