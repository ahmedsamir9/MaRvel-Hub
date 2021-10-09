package com.example.marvelhub.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvelhub.data.pagingremoteMedatiors.ComicsPagingSource
import com.example.marvelhub.data.pagingremoteMedatiors.EventsPagingSource
import com.example.marvelhub.data.pagingremoteMedatiors.SeriesPagingSource
import com.example.marvelhub.data.pagingremoteMedatiors.StoriesPagingSource
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.model.EventData
import com.example.marvelhub.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow

class EventsRepositoryImpl(private val localDataSource: LocalDataSource,private val remoteDataSource: RemoteDataSource):EventsRepository {
    override suspend fun getEventsOfCharacter(id: Int): Flow<PagingData<EventData>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {  EventsPagingSource(remoteDataSource,id)}
        ).flow
    }

    override suspend fun getComicsOfCharacter(id: Int): Flow<PagingData<EventData>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {  ComicsPagingSource(remoteDataSource,id)}
        ).flow
    }

    override suspend fun getStoriesOfCharacter(id: Int): Flow<PagingData<EventData>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {  StoriesPagingSource(remoteDataSource,id)}
        ).flow
    }

    override suspend fun getSeriesOfCharacter(id: Int): Flow<PagingData<EventData>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {  SeriesPagingSource(remoteDataSource,id)}
        ).flow
    }

}