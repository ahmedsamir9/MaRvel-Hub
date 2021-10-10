package com.example.marvelhub.data.repository
import com.example.marvelhub.data.remote.model.characterresponse.CharacterResponse
import com.example.marvelhub.data.remote.model.comicsresponse.ComicsResponse
import com.example.marvelhub.data.remote.model.eventresponse.EventResponse
import com.example.marvelhub.data.remote.model.seriesresponse.SeriesResponse
import com.example.marvelhub.data.remote.model.storiesresponse.StoriesResponse

interface RemoteDataSource {
   suspend fun getCharacters(offset :Int): CharacterResponse?
   suspend fun getCharactersByName(offset :Int,name:String): CharacterResponse?
   suspend fun getCharacterComics(id:Int,offset :Int): ComicsResponse?
   suspend fun getCharacterEvents(id:Int,offset :Int): EventResponse?
   suspend fun getCharacterSeries(id:Int,offset :Int): SeriesResponse?
   suspend fun getCharacterStories(id:Int,offset :Int): StoriesResponse?
}