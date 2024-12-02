package com.blackcube.starwars.ui.home

import androidx.lifecycle.viewModelScope
import com.blackcube.starwars.core.BaseViewModel
import com.blackcube.starwars.domain.usecases.api.PeopleUseCase
import com.blackcube.starwars.domain.usecases.api.StarshipsUseCase
import com.blackcube.starwars.ui.common.models.CompositeItem
import com.blackcube.starwars.ui.common.models.toCompositeItem
import com.blackcube.starwars.ui.common.models.CompositeItemType
import com.blackcube.starwars.ui.common.utils.UpdateNotifier
import com.blackcube.starwars.ui.home.store.models.HomeEffect
import com.blackcube.starwars.ui.home.store.models.HomeIntent
import com.blackcube.starwars.ui.home.store.models.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val peopleUseCase: PeopleUseCase,
    private val starshipsUseCase: StarshipsUseCase
) : BaseViewModel<HomeState, HomeEffect>(HomeState()) {

    private var jobChangeQuerySearch: Job? = null
    private var items: MutableList<CompositeItem> = mutableListOf()

    init {
        update()
    }

    fun update() {
        viewModelScope.launch {
            modifyState { copy(isLoading = true) }
            try {
                clearState()
                peopleUseCase.getPeoples().let { peoples ->
                    peoples.map { it.toCompositeItem() }.let { items.addAll(it) }
                }
                starshipsUseCase.getStarships().let { starships ->
                    starships.map { it.toCompositeItem() }.let { items.addAll(it) }
                }
                modifyState { copy(lists = items) }
            } catch (e: Exception) {
                e.printStackTrace()
                effect(HomeEffect.ShowToast("Ошибка получения данных\nСообщение:${e.message}"))
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

    fun handleIntent(homeIntent: HomeIntent) {
        when(homeIntent) {
            is HomeIntent.SearchQueryChanged -> {
                if (items.isNotEmpty()) onSearchQueryChanged(homeIntent.query)
            }
            is HomeIntent.OnFavouriteClick -> {
                onFavouriteClicked(
                    homeIntent.url,
                    homeIntent.itemType
                )
            }
            is HomeIntent.UpdateList -> update()
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

    private fun onFavouriteClicked(
        url: String,
        itemType: CompositeItemType
    ) {
        when(itemType) {
            CompositeItemType.People -> {
                viewModelScope.launch {
                    items = items.map { item ->
                        if (item.url == url) {
                            val foundPeople = item as CompositeItem.PeopleItem
                            if (!foundPeople.isFavourite) {
                                peopleUseCase.insert(url)
                                effect(HomeEffect.ShowToast("Карточка добавлена в избранное!"))
                            } else {
                                peopleUseCase.deleteByUrl(url)
                                effect(HomeEffect.ShowToast("Карточка удалена из избранного!"))
                            }
                            UpdateNotifier.notifyUpdate(UpdateNotifier.UpdateEvent.FromHome)
                            foundPeople.copy(isFavourite = !foundPeople.isFavourite)
                        } else {
                            item
                        }
                    }.also { modifyState { copy(lists = it) } }.toMutableList()
                }
            }
            CompositeItemType.Starship -> viewModelScope.launch {
                items = items.map { item ->
                    if (item.url == url) {
                        val foundStarship = item as CompositeItem.StarshipItem
                        if (!foundStarship.isFavourite) {
                            starshipsUseCase.insert(url)
                            effect(HomeEffect.ShowToast("Карточка добавлена в избранное!"))
                        } else {
                            starshipsUseCase.deleteByUrl(url)
                            effect(HomeEffect.ShowToast("Карточка удалена из избранного!"))
                        }
                        UpdateNotifier.notifyUpdate(UpdateNotifier.UpdateEvent.FromHome)
                        foundStarship.copy(isFavourite = !foundStarship.isFavourite)
                    } else {
                        item
                    }
                }.also { modifyState { copy(lists = it) } }.toMutableList()
            }
        }
    }

    companion object {
        private const val DELAY_QUERY_SEARCH = 200L
    }
}