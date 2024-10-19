package com.blackcube.starwars.domain.di

import com.blackcube.starwars.domain.usecases.api.PeopleUseCase
import com.blackcube.starwars.domain.usecases.api.StarshipsUseCase
import com.blackcube.starwars.domain.usecases.impl.PeopleUseCaseImpl
import com.blackcube.starwars.domain.usecases.impl.StarshipsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindPeopleUseCase(peopleUseCaseImpl: PeopleUseCaseImpl): PeopleUseCase

    @Binds
    fun bindStarshipsUseCase(starshipsUseCaseImpl: StarshipsUseCaseImpl): StarshipsUseCase

}
