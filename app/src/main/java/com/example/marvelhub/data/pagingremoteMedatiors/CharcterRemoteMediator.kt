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
            private val localDataSource: LocalDataSource,
            private val remoteDataSource: RemoteDataSource
        ): RemoteMediator<Int, CharacterEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): RemoteMediator.MediatorResult {

        return try {
            val key = 10
            when (loadType) {
                LoadType.REFRESH ->{
                     RemoteMediator.MediatorResult.Success(endOfPaginationReached = localDataSource.getOffsetValue()+1 < 0)
                }
                LoadType.PREPEND -> {
                    return RemoteMediator.MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return RemoteMediator.MediatorResult.Success(endOfPaginationReached = localDataSource.getOffsetValue()+1<0)
                }
            }
            var offset =localDataSource.getOffsetValue()
            val response = fetchCharacters(offset+1)
            val isEmpty = response.isEmpty()
            offset += 20
            localDataSource.setOffsetValue(offset)
            localDataSource.addCharacters(response)

            RemoteMediator.MediatorResult.Success(endOfPaginationReached = isEmpty)
        } catch (exception: IOException) {
            RemoteMediator.MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            RemoteMediator.MediatorResult.Error(exception)
        }

    }

    private fun getKeys() :Int = 20

    private suspend fun fetchCharacters(pageNumber:Int): List<CharacterEntity> {
        val response = remoteDataSource.getCharacters(pageNumber)
        return response!!.data.results.map { characterDto ->
            Mappers.fromCharacterDtoToCharacterEntity(characterDto)
        }
    }

}