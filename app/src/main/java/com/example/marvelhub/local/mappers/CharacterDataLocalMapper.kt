package com.example.marvelhub.local.mappers

import com.example.marvelhub.data.model.CharacterData
import com.example.marvelhub.local.model.CharacterEntity
import com.example.marvelhub.utils.Mapper
import kotlin.contracts.Returns

class CharacterDataLocalMapper:Mapper<CharacterEntity,CharacterData> {
    override fun from(data: CharacterEntity): CharacterData {
        return CharacterData(
            id = data.characterId,
            name = data.characterName,
            description = data.characterDescription,
            imagePath = data.characterImagePath
        )
    }

    override fun to(data: CharacterData): CharacterEntity {
        return CharacterEntity(
            characterId= data.id,
            characterName = data.name,
            characterDescription= data.description,
            characterImagePath = data.imagePath
        )
    }
}