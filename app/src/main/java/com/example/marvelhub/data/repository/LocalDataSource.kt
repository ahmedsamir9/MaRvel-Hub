package com.example.marvelhub.data.repository

import androidx.paging.PagingSource
import com.example.marvelhub.data.local.model.CharacterEntity
import com.example.marvelhub.data.local.model.CharacterRemoteKey
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addCharacters(character :List<CharacterEntity>);
    fun getCharacter(characterId: Int): Flow<CharacterEntity>
     fun getCharacters(): PagingSource<Int, CharacterEntity>
     fun setOffsetValue(offset: Int)
     fun getOffsetValue():Int
     fun getCharactersRemoteKeys():List<CharacterRemoteKey>
     fun getCharacterDataById(characterId:Int):CharacterEntity

}