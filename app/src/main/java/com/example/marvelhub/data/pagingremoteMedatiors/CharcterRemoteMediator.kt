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
import java.io.IOException

@ExperimentalPagingApi
data class CharacterRemoteMediator (
            val localDataSource: LocalDataSource,
            val remoteDataSource: RemoteDataSource
        ): RemoteMediator<Int, CharacterEntity>() {
    val key = Constants.BASE_OFFSET;
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
            var offset =localDataSource.getOffsetValue()
            val response = fetchCharacters(offset+1)
            offset += 20
            localDataSource.setOffsetValue(offset)
            localDataSource.addCharacters(response)
            val isEmpty = response.isEmpty()
            RemoteMediator.MediatorResult.Success(endOfPaginationReached = isEmpty)
        } catch (exception: IOException) {
            RemoteMediator.MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            RemoteMediator.MediatorResult.Error(exception)
        }

    }
    private suspend fun fetchCharacters(pageNumber:Int): List<CharacterEntity> {
        val response = remoteDataSource.getCharacters(pageNumber)
        return response!!.data.results.map { characterDto ->
            Mappers.fromCharacterDtoToCharacterEntity(characterDto)
        }
    }

}