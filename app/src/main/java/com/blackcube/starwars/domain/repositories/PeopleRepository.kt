package com.blackcube.starwars.domain.repositories

import com.blackcube.starwars.domain.models.PeopleModel
import com.blackcube.starwars.domain.models.PeoplesListModel
import com.blackcube.starwars.local.impl.database.models.PeopleItemDto

interface PeopleRepository {
    suspend fun getPeoples(): PeoplesListModel

    suspend fun insert(url: String)

    suspend fun deleteByUrl(url: String)

    suspend fun update(item: PeopleModel)
}