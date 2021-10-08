package com.example.marvelhub.data.repository

import androidx.paging.PagingSource
import com.example.marvelhub.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addCharacters(character :List<CharacterEntity>);
    fun getCharacter(characterId: Int): Flow<CharacterEntity>
     fun getCharacters(offset :Int): List<CharacterEntity>
     fun setOffsetValue(offset: Int)
     fun getOffsetValue():Int

}