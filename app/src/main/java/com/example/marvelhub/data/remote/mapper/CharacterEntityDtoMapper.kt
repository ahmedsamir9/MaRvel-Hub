package com.example.marvelhub.data.remote.mapper

import com.example.marvelhub.data.local.model.CharacterEntity
import com.example.marvelhub.data.remote.model.CharacterDto
import com.example.marvelhub.data.remote.model.Thumbnail
import com.example.marvelhub.utils.Mapper
import javax.inject.Inject

class CharacterEntityDtoMapper @Inject constructor(){
fun from(data: CharacterDto): CharacterEntity {
        return CharacterEntity(
            characterId = data.id,
            characterImagePath = imagePathConncatenator(data.thumbnail),
            characterDescription = fillCharacterEmptyDescription(data.description),
            characterName = data.name
        )
    }
}
private fun fillCharacterEmptyDescription(description:String): String {
    var data = description
   if (description.isNullOrBlank())
       data = "Sorry We Have No Data For This Character"
    return data
}
private fun imagePathConncatenator(image : Thumbnail): String {
    return image.path+"."+image.extension;
}