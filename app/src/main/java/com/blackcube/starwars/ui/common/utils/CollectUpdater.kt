package com.blackcube.starwars.ui.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T> CollectUpdater(
    updateFlow: StateFlow<T>,
    resetEvent: T,
    onUpdate: (T) -> Unit
) {
    val updateEvent by updateFlow.collectAsState()

    LaunchedEffect(updateEvent) {
        if (updateEvent != resetEvent) {
            onUpdate(updateEvent)
        }
    }
}

object UpdateNotifier {
    private val _updateFlow = MutableStateFlow<UpdateEvent>(UpdateEvent.None)
    val updateFlow: StateFlow<UpdateEvent> = _updateFlow

    fun notifyUpdate(event: UpdateEvent) {
        _updateFlow.value = event
    }

    fun resetUpdate() {
        _updateFlow.value = UpdateEvent.None
    }

    sealed class UpdateEvent {
        data object None : UpdateEvent()
        data object FromHome : UpdateEvent()
        data object FromFavourite : UpdateEvent()
    }
}
