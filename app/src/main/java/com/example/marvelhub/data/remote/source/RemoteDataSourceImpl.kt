package com.example.marvelhub.data.remote.source


import com.example.marvelhub.data.remote.MarvelService

import com.example.marvelhub.data.remote.model.characterresponse.CharacterResponse
import com.example.marvelhub.data.remote.model.comicsresponse.ComicsResponse
import com.example.marvelhub.data.remote.model.eventresponse.EventResponse
import com.example.marvelhub.data.remote.model.seriesresponse.SeriesResponse
import com.example.marvelhub.data.remote.model.storiesresponse.StoriesResponse
import com.example.marvelhub.data.repository.RemoteDataSource

class RemoteDataSourceImpl (private val marvelService: MarvelService): RemoteDataSource {
    override suspend fun getCharacters(offset: Int): CharacterResponse? {
        return marvelService.getCharacters(offset)
    }

    override suspend fun getCharacterComics(id: Int, offset: Int): ComicsResponse? {
        return marvelService.getCharacterComics(id,offset)
    }

    override suspend fun getCharacterEvents(id: Int, offset: Int): EventResponse? {
        return marvelService.getCharacterEvents(id,offset)
    }

    override suspend fun getCharacterSeries(id: Int, offset: Int): SeriesResponse? {
        return marvelService.getCharacterSeries(id,offset)
    }

    override suspend fun getCharacterStories(id: Int, offset: Int): StoriesResponse? {
        return marvelService.getCharacterStories(id,offset)
    }
}