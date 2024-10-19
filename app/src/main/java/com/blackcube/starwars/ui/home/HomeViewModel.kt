package com.blackcube.starwars.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.blackcube.starwars.core.BaseViewModel
import com.blackcube.starwars.domain.usecases.api.PeopleUseCase
import com.blackcube.starwars.domain.usecases.api.StarshipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val peopleUseCase: PeopleUseCase,
    private val starshipsUseCase: StarshipsUseCase
) : BaseViewModel<HomeViewModel.State, HomeViewModel.Actions>(State()) {

    init {
        viewModelScope.launch {
            try {
                val list = peopleUseCase()
                Log.d("debugTag", list.toString())
                val list2 = starshipsUseCase()
                Log.d("debugTag", list2.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                onAction(Actions.ShowToast)
            }
        }
    }

    data class State(
        val convertAmount: Double = 0.0,
        val currencyCount: Int = 1
    )

    sealed interface Actions {
        data object GoToLiveCurrencies : Actions
        data object ShowToast : Actions
    }
}