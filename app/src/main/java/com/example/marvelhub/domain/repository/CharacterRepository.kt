package com.example.marvelhub.domain.repository

import com.example.marvelhub.domain.model.Character
import kotlinx.coroutines.flow.Flow
interface CharacterRepository {
    fun getCharacters(): Flow<List<Character>>
}