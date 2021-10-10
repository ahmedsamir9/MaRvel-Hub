package com.example.marvelhub.domain.useCases

import com.example.marvelhub.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersByNameUseCase @Inject constructor(private val characterRepository: CharacterRepository ) {
    suspend operator fun invoke(query:String) = characterRepository.getCharactersByName(query)
}