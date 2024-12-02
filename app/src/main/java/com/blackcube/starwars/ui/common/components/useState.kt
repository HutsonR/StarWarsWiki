package com.blackcube.starwars.ui.common.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun <T : Any?> useState(initialValue: T): MutableState<T> {
    val state = rememberSaveable { mutableStateOf(initialValue) }
    return state
}