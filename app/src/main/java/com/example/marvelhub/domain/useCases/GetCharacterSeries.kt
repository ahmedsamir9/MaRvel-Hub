package com.example.marvelhub.domain.useCases

import com.example.marvelhub.domain.repository.CharacterRepository
import com.example.marvelhub.domain.repository.EventsRepository
import javax.inject.Inject

class GetCharacterSeries @Inject constructor(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke(characterId:Int) = eventsRepository.getSeriesOfCharacter(characterId)
}