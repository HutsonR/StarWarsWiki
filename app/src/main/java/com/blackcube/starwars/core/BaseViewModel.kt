package com.blackcube.starwars.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State, Actions>(initialState: State) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    private val _action: MutableSharedFlow<Actions> = MutableSharedFlow(replay = 1)
    val action: Flow<Actions> = _action.onEach { _action.resetReplayCache() }

    /**
     * Получение internalState
     */
    protected fun getState(): State {
        return _state.value
    }

    /**
     * Use copy function to change state properties
     *
     * Example: modifyState { copy(loginError = "Ошибка!") }
     */
    protected fun modifyState(block: State.() -> State) {
        _state.update(block)
    }

    /**
     * Отправка события
     */
    protected fun onAction(action: Actions) {
        _action.tryEmit(action)
    }

}