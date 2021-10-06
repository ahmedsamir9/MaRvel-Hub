package com.example.marvelhub.data.repository

import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl : CharacterRepository {
    override fun getCharacters(): Flow<List<Character>> {
        TODO("Not yet implemented")
    }
}