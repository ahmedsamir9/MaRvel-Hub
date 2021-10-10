package com.example.marvelhub.data.repository

import androidx.paging.PagingSource
import com.example.marvelhub.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addCharacters(character :List<CharacterEntity>);
    fun getCharacter(characterId: Int): Flow<CharacterEntity>
     fun getCharacters(): PagingSource<Int, CharacterEntity>
     fun setOffsetValue(offset: Int)
     fun getOffsetValue():Int
     fun getCharacterDataById(characterId:Int):CharacterEntity
     fun getCharacterDataByName(characterName:String): PagingSource<Int, CharacterEntity>

}