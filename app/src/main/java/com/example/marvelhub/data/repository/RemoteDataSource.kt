package com.example.marvelhub.data.repository
import com.example.marvelhub.data.remote.model.characterresponse.CharacterResponse

interface RemoteDataSource {
   suspend fun getCharacters(offset :Int): CharacterResponse?
}