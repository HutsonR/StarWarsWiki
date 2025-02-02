package com.blackcube.starwars.remote.di

import android.content.Context
import com.blackcube.starwars.remote.api.data_sources.StarWarsRemoteDataSource
import com.blackcube.starwars.remote.impl.api.StarWarsApi
import com.blackcube.starwars.remote.impl.data_sources.StarWarsRemoteDataSourceImpl
import com.blackcube.starwars.remote.impl.providers.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    fun provideNetworkProvider(@ApplicationContext context: Context): NetworkProvider {
        return NetworkProvider(context)
    }

    @Provides
    @Singleton
    fun getStarWarsApi(provider: NetworkProvider): StarWarsApi =
        provider.createStarWarsApi()

    @Provides
    @Singleton
    fun provideStarWarsRemoteDataSource(starWarsApi: StarWarsApi): StarWarsRemoteDataSource {
        return StarWarsRemoteDataSourceImpl(starWarsApi)
    }

}
