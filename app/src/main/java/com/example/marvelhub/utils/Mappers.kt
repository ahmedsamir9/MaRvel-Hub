package com.example.marvelhub.utils
import com.example.marvelhub.data.local.model.CharacterEntity
import com.example.marvelhub.data.remote.model.characterresponse.CharacterDto
import com.example.marvelhub.data.remote.model.comicsresponse.ComicsDto
import com.example.marvelhub.data.remote.model.eventresponse.EventDto
import com.example.marvelhub.data.remote.model.eventresponse.Stories
import com.example.marvelhub.data.remote.model.seriesresponse.SeriesDto
import com.example.marvelhub.data.remote.model.storiesresponse.StoriesDto
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.model.EventData

object Mappers {
    fun fromEventDtoToEventData(eventDto:EventDto) =
        EventData(eventDto.title, String().imagePathConcatenation(eventDto.thumbnail.path,eventDto.thumbnail.extension),eventDto.id)
    fun fromComicsDtoToEventData(comicsDto: ComicsDto) =
        EventData(comicsDto.title, String().imagePathConcatenation(comicsDto.thumbnail.path,comicsDto.thumbnail.extension),comicsDto.id)
    fun fromCharacterEntityToCharacter(characterEntity:CharacterEntity) = Character(
        id = characterEntity.characterId,
        name = characterEntity.characterName,
        description = characterEntity.characterDescription,
        imagePath = characterEntity.characterImagePath
    )
    fun fromCharacterDtoToCharacterEntity(characterDto: CharacterDto)=CharacterEntity(
        characterId = characterDto.id,
        characterImagePath = String().imagePathConcatenation(characterDto.thumbnail.path,characterDto.thumbnail.extension),
        characterDescription = characterDto.description.fillCharacterEmptyDescription(),
        characterName = characterDto.name
    )
    fun fromSeriesDtoToEventData(seriesDto:SeriesDto) =
        EventData(seriesDto.title, String().imagePathConcatenation(seriesDto.thumbnail.path,seriesDto.thumbnail.extension),seriesDto.id)
    fun fromStoriesDtoToEventData(storiesDto: StoriesDto) =
        EventData(storiesDto.title, String().imagePathConcatenation(storiesDto.thumbnail.path,storiesDto.thumbnail.extension),storiesDto.id)
}