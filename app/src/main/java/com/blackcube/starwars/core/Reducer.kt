package com.blackcube.starwars.core

interface Reducer<State, Event> {
    fun reduce(state: State, event: Event): State
}