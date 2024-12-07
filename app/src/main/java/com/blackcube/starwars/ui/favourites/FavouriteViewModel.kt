package com.blackcube.starwars.ui.favourites

import androidx.lifecycle.viewModelScope
import com.blackcube.starwars.core.BaseViewModel
import com.blackcube.starwars.domain.usecases.api.PeopleUseCase
import com.blackcube.starwars.domain.usecases.api.StarshipsUseCase
import com.blackcube.starwars.ui.common.models.CompositeItem
import com.blackcube.starwars.ui.common.models.toCompositeItem
import com.blackcube.starwars.ui.common.models.CompositeItemType
import com.blackcube.starwars.ui.common.utils.UpdateNotifier
import com.blackcube.starwars.ui.favourites.store.models.FavouriteEffect
import com.blackcube.starwars.ui.favourites.store.models.FavouriteIntent
import com.blackcube.starwars.ui.favourites.store.models.FavouriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val peopleUseCase: PeopleUseCase,
    private val starshipsUseCase: StarshipsUseCase
) : BaseViewModel<FavouriteState, FavouriteEffect>(FavouriteState()) {

    private var jobChangeQuerySearch: Job? = null
    private var items: MutableList<CompositeItem> = mutableListOf()

    init {
        update()
    }

    private fun update() {
        viewModelScope.launch {
            modifyState { copy(isLoading = true) }
            try {
                clearState()
                peopleUseCase.getPeoples().let { peoples ->
                    peoples.filter { it.isFavourite }.map { it.toCompositeItem() }.let { items.addAll(it) }
                }
                starshipsUseCase.getStarships().let { starships ->
                    starships.filter { it.isFavourite }.map { it.toCompositeItem() }.let { items.addAll(it) }
                }
                modifyState { copy(lists = items) }
            } catch (e: Exception) {
                e.printStackTrace()
                effect(FavouriteEffect.ShowToast("Ошибка получения данных\nСообщение:${e.message}"))
            } finally {
                modifyState { copy(isLoading = false) }
            }
        }
    }

    private fun clearState() {
        items.clear()
        modifyState {
            copy(
                lists = emptyList(),
                searchQuery = ""
            )
        }
    }

    fun handleIntent(favouriteIntent: FavouriteIntent) {
        when(favouriteIntent) {
            is FavouriteIntent.SearchQueryChanged -> {
                if (items.isNotEmpty()) onSearchQueryChanged(favouriteIntent.query)
            }

            is FavouriteIntent.OnFavouriteClick -> {
                onFavouriteClicked(
                    favouriteIntent.url,
                    favouriteIntent.itemType
                )
            }

            FavouriteIntent.UpdateList -> update()

            is FavouriteIntent.OnItemClick -> effect(FavouriteEffect.NavigateToDetails(favouriteIntent.itemId))
        }
    }

    private fun onSearchQueryChanged(query: String) {
        jobChangeQuerySearch?.cancel()
        jobChangeQuerySearch = viewModelScope.launch {
            modifyState { copy(searchQuery = query) }
            delay(DELAY_QUERY_SEARCH)
            modifyState { copy(isLoading = true) }

            val filteredList = if (query.isBlank()) {
                items
            } else {
                items.filter { it.name.contains(query, ignoreCase = true) }
            }

            modifyState { copy(lists = filteredList, isLoading = false) }
        }
    }

    private fun copyList(list: List<CompositeItem>): List<CompositeItem> {
        return list.map {
            when (it) {
                is CompositeItem.PeopleItem -> it.copy()
                is CompositeItem.StarshipItem -> it.copy()
            }
        }.toList()
    }


    private fun onFavouriteClicked(
        url: String,
        itemType: CompositeItemType
    ) {
        when(itemType) {
            CompositeItemType.People -> {
                viewModelScope.launch {
                    if(items.removeIf { it.url == url }) {
                        peopleUseCase.deleteByUrl(url)
                        modifyState { copy(lists = copyList(items)) }
                        UpdateNotifier.notifyUpdate(UpdateNotifier.UpdateEvent.FromFavourite)
                        effect(FavouriteEffect.ShowToast("Карточка удалена из избранного!"))
                    }
                }
            }
            CompositeItemType.Starship -> {
                viewModelScope.launch {
                    if(items.removeIf { it.url == url }) {
                        starshipsUseCase.deleteByUrl(url)
                        modifyState { copy(lists = copyList(items)) }
                        UpdateNotifier.notifyUpdate(UpdateNotifier.UpdateEvent.FromFavourite)
                        effect(FavouriteEffect.ShowToast("Карточка удалена из избранного!"))
                    }
                }
            }
        }
    }

    companion object {
        private const val DELAY_QUERY_SEARCH = 200L
    }
}