package com.example.marvelhub.data.repository



import com.example.marvelhub.data.local.mappers.CharacterDomainLocalMapper
import com.example.marvelhub.data.local.model.CharacterEntity
import com.example.marvelhub.data.remote.mapper.CharacterEntityDtoMapper
import com.example.marvelhub.data.remote.model.CharacterDto
import com.example.marvelhub.data.remote.model.CharacterResponse
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.repository.CharacterRepository
import com.example.marvelhub.utils.ApiResult
import com.example.marvelhub.utils.DataState
import com.example.marvelhub.utils.NetworkBoundResource
import kotlinx.coroutines.flow.*

import timber.log.Timber


class CharacterRepositoryImpl (private val localDataSource: LocalDataSource,private val remoteDataSource: RemoteDataSource): CharacterRepository {
  private val mapper = CharacterDomainLocalMapper()
    val characterEntityDtoMapper= CharacterEntityDtoMapper()
    private var dataBaseOffset = 0;
    override suspend fun getCharacters(): Flow<DataState<List<Character>>> = flow {
        emit(DataState.loading(listOf()))
        val dbValue = getCharactersFromDb(dataBaseOffset)
        val offset = localDataSource.getOffsetValue()
        Timber.tag("smsms").d("UI"+offset.toString())
        Timber.tag("smsms").d("UI"+dataBaseOffset.toString())
        if (dbValue.size <= 20) {
            val apiResponse =getCharactersFromNetwork(offset)
            when (apiResponse) {
                is ApiResult.Success -> {
                    val newOffset =offset+20
                    Timber.tag("smsms").d(offset.toString())
                    localDataSource.setOffsetValue(newOffset)
                    insertCharactersInDb(apiResponse.value!!.data.results)
                    val oldDbOffset=dataBaseOffset
                    dataBaseOffset+=20
                    emit(DataState.success(getCharactersFromDb(oldDbOffset)))
                }
                is ApiResult.GenericError -> {
                    emit(DataState.error(apiResponse.errorMessage!!, listOf<Character>()))
                }
            }
        } else {
            dataBaseOffset+=20
            emit( DataState.success(dbValue))
        }
    }
    private fun getCharactersFromDb(offset:Int): List<Character> {
        return localDataSource.getCharacters(offset = offset).map { list->
           mapper.from(list)
        }

    }
    private suspend fun getCharactersFromNetwork(offset:Int): ApiResult<CharacterResponse?> {
        return remoteDataSource.getCharacters(offset+1)
    }
    private suspend fun insertCharactersInDb(list:List<CharacterDto>){
        localDataSource.addCharacters(list.map { characterEntityDtoMapper.from(it) })
    }
}