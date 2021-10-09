package com.example.marvelhub.data.local.source

import androidx.paging.PagingSource
import com.example.marvelhub.data.local.MarvelDataBase
import com.example.marvelhub.data.local.PreferenceManager
import com.example.marvelhub.data.repository.LocalDataSource

import com.example.marvelhub.data.local.model.CharacterEntity
import com.example.marvelhub.data.local.model.CharacterRemoteKey
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl constructor(private val marvelDataBase: MarvelDataBase ,private val preferenceManager: PreferenceManager) : LocalDataSource {
    override suspend fun addCharacters(character: List<CharacterEntity>) = marvelDataBase.characterDao().insertCharacters(character)
    override fun getCharacter(characterId: Int): Flow<CharacterEntity> = marvelDataBase.characterDao().getCharacter(id = characterId)
    override fun getCharacters() = marvelDataBase.characterDao().getCharacters()
    override fun setOffsetValue(offset: Int)= preferenceManager.setOffsetValue(offset)
    override fun getOffsetValue(): Int = preferenceManager.getOffsetValue()
    override fun getCharactersRemoteKeys(): List<CharacterRemoteKey> = marvelDataBase.characterDao().getCharacterKeys()
    override fun getCharacterDataById(characterId: Int) = marvelDataBase.characterDao().getCharacterById(characterId)
}