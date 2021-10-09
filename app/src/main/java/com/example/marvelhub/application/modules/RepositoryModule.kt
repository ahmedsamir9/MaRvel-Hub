package com.example.marvelhub.application.modules

import com.example.marvelhub.data.repository.CharacterRepositoryImpl
import com.example.marvelhub.data.repository.EventsRepositoryImpl
import com.example.marvelhub.data.repository.LocalDataSource
import com.example.marvelhub.data.repository.RemoteDataSource
import com.example.marvelhub.domain.repository.CharacterRepository
import com.example.marvelhub.domain.repository.EventsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class  RepositoryModule{
    @ViewModelScoped
    @Provides
    fun characterRepositoryProvider(localDataSource: LocalDataSource,remoteDataSource: RemoteDataSource):CharacterRepository=
        CharacterRepositoryImpl(localDataSource,remoteDataSource)
    @ViewModelScoped
    @Provides
    fun eventsRepositoryProvider(localDataSource: LocalDataSource,remoteDataSource: RemoteDataSource):EventsRepository=
        EventsRepositoryImpl(localDataSource,remoteDataSource)

}