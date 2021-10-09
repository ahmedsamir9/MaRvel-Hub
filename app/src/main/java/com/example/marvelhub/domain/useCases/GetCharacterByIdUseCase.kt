package com.example.marvelhub.domain.useCases

import com.example.marvelhub.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val characterRepository: CharacterRepository ) {
    suspend operator fun invoke(id:Int) = characterRepository.getCharacterById(id)
}