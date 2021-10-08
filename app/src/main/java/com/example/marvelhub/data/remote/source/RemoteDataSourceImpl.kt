package com.example.marvelhub.data.remote.source


import com.example.marvelhub.data.local.model.CharacterEntity
import com.example.marvelhub.data.remote.MarvelService
import com.example.marvelhub.data.remote.mapper.CharacterEntityDtoMapper
import com.example.marvelhub.data.remote.model.CharacterResponse
import com.example.marvelhub.data.repository.RemoteDataSource
import com.example.marvelhub.utils.ApiResult
import com.example.marvelhub.utils.safeApiCall
import timber.log.Timber
import java.lang.Exception

class RemoteDataSourceImpl (private val marvelService: MarvelService,  val characterEntityDtoMapper: CharacterEntityDtoMapper): RemoteDataSource {
    override suspend fun getCharacters(offset: Int): ApiResult<CharacterResponse?> {
        return safeApiCall { marvelService.getCharacters(offset) }
    }
}