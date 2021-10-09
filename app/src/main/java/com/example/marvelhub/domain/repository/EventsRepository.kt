package com.example.marvelhub.domain.repository

import androidx.paging.PagingData
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.model.EventData
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    suspend fun getEventsOfCharacter(id:Int): Flow<PagingData<EventData>>
    suspend fun getComicsOfCharacter(id:Int): Flow<PagingData<EventData>>
    suspend fun getStoriesOfCharacter(id:Int): Flow<PagingData<EventData>>
    suspend fun getSeriesOfCharacter(id:Int): Flow<PagingData<EventData>>
}