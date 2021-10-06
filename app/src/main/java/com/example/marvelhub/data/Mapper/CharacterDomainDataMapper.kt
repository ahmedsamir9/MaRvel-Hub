package com.example.marvelhub.data.Mapper

import com.example.marvelhub.data.model.CharacterData
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.utils.Mapper

class CharacterDomainDataMapper:Mapper<CharacterData,Character> {
    override fun from(data: CharacterData): Character {
        return Character(
            name = data.name,
            id = data.id,
            imagePath = data.imagePath,
            description = data.description
        )
    }

    override fun to(data: Character): CharacterData {
       return CharacterData(
           name = data.name,
           id = data.id,
           imagePath = data.imagePath,
           description = data.description
       )
    }
}