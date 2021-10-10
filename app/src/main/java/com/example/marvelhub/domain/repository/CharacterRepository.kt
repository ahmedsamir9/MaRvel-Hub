package com.example.marvelhub.domain.repository

import androidx.paging.PagingData
import com.example.marvelhub.domain.model.Character

import kotlinx.coroutines.flow.Flow
interface CharacterRepository {
    suspend fun getCharacters(): Flow<PagingData<Character>>
    suspend fun getCharacterById(id :Int): Character
    suspend fun getCharactersByName(query :String): Flow<PagingData<Character>>
}