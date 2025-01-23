package com.blackcube.starwars.ui.details

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blackcube.starwars.core.BaseViewModel
import com.blackcube.starwars.domain.models.DomainModel
import com.blackcube.starwars.domain.models.PeopleModel
import com.blackcube.starwars.domain.models.StarshipModel
import com.blackcube.starwars.domain.usecases.api.PeopleUseCase
import com.blackcube.starwars.domain.usecases.api.StarshipsUseCase
import com.blackcube.starwars.ui.common.models.CompositeItem
import com.blackcube.starwars.ui.common.models.CompositeItemType
import com.blackcube.starwars.ui.common.models.toCompositeItem
import com.blackcube.starwars.ui.common.utils.UpdateNotifier
import com.blackcube.starwars.ui.details.store.models.DetailsEffect
import com.blackcube.starwars.ui.details.store.models.DetailsIntent
import com.blackcube.starwars.ui.details.store.models.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val peopleUseCase: PeopleUseCase,
    private val starshipsUseCase: StarshipsUseCase
) : BaseViewModel<DetailsState, DetailsEffect>(DetailsState()) {

    fun init(itemId: String) {
        viewModelScope.launch {
            try {
                val item = if(itemId.contains("people")) {
                    peopleUseCase.getPeoples().let { peoples ->
                        peoples.find { it.url == itemId }
                    }
                } else {
                    starshipsUseCase.getStarships().let { starships ->
                        starships.find { it.url == itemId }
                    }
                }

                item?.let {
                    Log.d("debugTag", "item: $it")
                    modifyState { copy(item = it) }
                } ?: effect(DetailsEffect.ShowToast("Карточка не найдена"))
            } catch (e: Exception) {
                e.printStackTrace()
                effect(DetailsEffect.ShowToast("Ошибка получения данных\nСообщение:${e.message}"))
            }
        }
    }

    fun handleIntent(detailsIntent: DetailsIntent) = Unit

}