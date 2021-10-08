package com.example.marvelhub.application.modules

import android.content.Context
import androidx.room.Room
import com.example.marvelhub.data.local.MarvelDataBase
import com.example.marvelhub.data.local.PreferenceManager
import com.example.marvelhub.data.local.doa.CharacterDao
import com.example.marvelhub.data.local.source.LocalDataSourceImpl
import com.example.marvelhub.data.repository.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideMarVelDatabase(@ApplicationContext context: Context):MarvelDataBase=
        Room.databaseBuilder(context,MarvelDataBase::class.java,"marvelDB").allowMainThreadQueries().build()
    @Singleton
    @Provides
    fun provideCharacterDao(dataBase: MarvelDataBase)=dataBase.characterDao()
    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context)= PreferenceManager(context)
    @Singleton
    @Provides
    fun provideLocalDataSource(marvelDataBase: MarvelDataBase,preferenceManager: PreferenceManager):LocalDataSource= LocalDataSourceImpl(marvelDataBase,preferenceManager)
}