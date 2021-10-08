package com.example.marvelhub.data.local.mappers

import com.example.marvelhub.data.local.model.CharacterEntity
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.utils.Mapper
import javax.inject.Inject

class CharacterDomainLocalMapper @Inject constructor():Mapper<CharacterEntity,Character> {
    override fun from(data: CharacterEntity): Character {
        return Character(
            id = data.characterId,
            name = data.characterName,
            description = data.characterDescription,
            imagePath = data.characterImagePath
        )
    }

    override fun to(data: Character): CharacterEntity {
        return CharacterEntity(
            characterId= data.id,
            characterName = data.name,
            characterDescription= data.description,
            characterImagePath = data.imagePath
        )
    }
}