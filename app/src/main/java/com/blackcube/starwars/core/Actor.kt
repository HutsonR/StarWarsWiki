package com.blackcube.starwars.core

import kotlinx.coroutines.flow.Flow

interface Actor<Command, Event> {
    fun invoke(command: Command): Flow<Event>
}