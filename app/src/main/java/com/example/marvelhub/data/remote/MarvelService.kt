package com.example.marvelhub.data.remote

import com.example.marvelhub.data.remote.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
        @GET("characters")
        suspend fun getCharacters(@Query("offset")offset:Int,@Query("limit")limit:Int=20):CharacterResponse?
}