package com.example.marvelhub.data.remote

import com.example.marvelhub.data.remote.model.characterresponse.CharacterResponse
import com.example.marvelhub.data.remote.model.comicsresponse.ComicsResponse
import com.example.marvelhub.data.remote.model.eventresponse.EventResponse
import com.example.marvelhub.data.remote.model.seriesresponse.SeriesResponse
import com.example.marvelhub.data.remote.model.storiesresponse.StoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {
        @GET("characters")
        suspend fun getCharacters(@Query("offset")offset:Int,@Query("limit")limit:Int=20): CharacterResponse?
        @GET("characters/{characterId}/comics")
        suspend fun getCharacterComics(@Path("characterId")characterId:Int,@Query("offset")offset:Int):ComicsResponse
        @GET("characters/{characterId}/series")
        suspend fun getCharacterSeries(@Path("characterId")characterId:Int,@Query("offset")offset:Int):SeriesResponse
        @GET("characters/{characterId}/events")
        suspend fun getCharacterEvents(@Path("characterId")characterId:Int,@Query("offset")offset:Int):EventResponse
        @GET("characters/{characterId}/stories")
        suspend fun getCharacterStories(@Path("characterId")characterId:Int,@Query("offset")offset:Int):StoriesResponse
        @GET("characters")
        suspend fun getCharactersByName(@Query("offset")offset:Int,@Query("limit")limit:Int=20,@Query("nameStartsWith")name:String): CharacterResponse?

}