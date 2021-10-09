package com.example.marvelhub.data.remote.source


import com.example.marvelhub.data.remote.MarvelService
import com.example.marvelhub.data.remote.mapper.CharacterEntityDtoMapper
import com.example.marvelhub.data.remote.model.characterresponse.CharacterResponse
import com.example.marvelhub.data.repository.RemoteDataSource

class RemoteDataSourceImpl (private val marvelService: MarvelService,  val characterEntityDtoMapper: CharacterEntityDtoMapper): RemoteDataSource {
    override suspend fun getCharacters(offset: Int): CharacterResponse? {
        return marvelService.getCharacters(offset)
    }
}