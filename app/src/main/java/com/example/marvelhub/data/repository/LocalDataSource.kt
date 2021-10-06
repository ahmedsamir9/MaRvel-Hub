package com.example.marvelhub.data.repository

import androidx.paging.PagingSource
import com.example.marvelhub.data.model.CharacterData

interface LocalDataSource {
    suspend fun addCharacters(character :List<CharacterData>);
    suspend fun getCharacter(characterId:Int):CharacterData
    suspend fun getCharacters():PagingSource<Int,CharacterData>
}