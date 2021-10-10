package com.example.marvelhub.data.pagingremoteMedatiors

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.marvelhub.data.local.model.CharacterEntity

import com.example.marvelhub.data.repository.LocalDataSource
import com.example.marvelhub.data.repository.RemoteDataSource
import com.example.marvelhub.utils.Constants
import com.example.marvelhub.utils.Mappers
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@ExperimentalPagingApi
data class CharactersByNameRemoteMediator (
             private val localDataSource: LocalDataSource,
           private val remoteDataSource: RemoteDataSource,
            private val query:String,
        ): RemoteMediator<Int, CharacterEntity>() {
   private var apiOffset = Constants.BASE_OFFSET
   private var baseQuery = Constants.BASE_Query
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): RemoteMediator.MediatorResult {
        return try {
            val loadKeys= when (loadType) {
                LoadType.REFRESH ->{
                    null
                }
                LoadType.PREPEND -> {
                    return RemoteMediator.MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return RemoteMediator.MediatorResult.Success(endOfPaginationReached = true)
                }
            }
            if (query != baseQuery){
                Timber.tag("chara").d("Here "+apiOffset)
                apiOffset = 0;
                baseQuery = query
            }
            val response = fetchCharacters(apiOffset+1,baseQuery)
            apiOffset+=20
            localDataSource.addCharacters(response)
            val isEmpty = response.isEmpty()
            RemoteMediator.MediatorResult.Success(endOfPaginationReached = isEmpty)
        } catch (exception: IOException) {
            RemoteMediator.MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            RemoteMediator.MediatorResult.Error(exception)
        }

    }
    private suspend fun fetchCharacters(pageNumber:Int,query: String): List<CharacterEntity> {
        val response = remoteDataSource.getCharactersByName(pageNumber,query)
        return response!!.data.results.map { characterDto ->
            Mappers.fromCharacterDtoToCharacterEntity(characterDto)
        }
    }

}