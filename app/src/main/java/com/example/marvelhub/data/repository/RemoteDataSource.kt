package com.example.marvelhub.data.repository
import com.example.marvelhub.data.local.model.CharacterEntity
import com.example.marvelhub.data.remote.model.CharacterResponse
import com.example.marvelhub.utils.ApiResult

interface RemoteDataSource {
   suspend fun getCharacters(offset :Int): ApiResult<CharacterResponse?>
}