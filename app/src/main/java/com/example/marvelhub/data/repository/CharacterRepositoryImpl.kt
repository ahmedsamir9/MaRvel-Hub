package com.example.marvelhub.data.repository
import androidx.paging.*
import com.example.marvelhub.data.pagingremoteMedatiors.CharacterRemoteMediator
import com.example.marvelhub.data.pagingremoteMedatiors.CharactersByNameRemoteMediator
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.repository.CharacterRepository
import com.example.marvelhub.utils.Mappers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.util.*


class CharacterRepositoryImpl (private val localDataSource: LocalDataSource,private val remoteDataSource: RemoteDataSource): CharacterRepository {
    @ExperimentalPagingApi
    override suspend fun getCharacters(): Flow<PagingData<Character>> {
      return  Pager(
        config =  PagingConfig(
          pageSize =10,
          enablePlaceholders = false) ,
        remoteMediator = CharacterRemoteMediator(localDataSource,remoteDataSource),
        pagingSourceFactory = {localDataSource.getCharacters()}
      ).flow.map { paging ->
        paging.map { entity -> Mappers.fromCharacterEntityToCharacter(entity) }
      }
    }

  override suspend fun getCharacterById(id: Int): Character {
      return Mappers.fromCharacterEntityToCharacter(localDataSource.getCharacterDataById(id))
  }

    @ExperimentalPagingApi
    override suspend fun getCharactersByName(query: String): Flow<PagingData<Character>> {
        return  Pager(
            config =  PagingConfig(
                pageSize =10,
                enablePlaceholders = false) ,
            remoteMediator = CharactersByNameRemoteMediator(localDataSource,remoteDataSource,query),
            pagingSourceFactory = { localDataSource.getCharacterDataByName(query)
            }
        ).flow.map { paging ->
            paging.map { entity -> Mappers.fromCharacterEntityToCharacter(entity) }
        }
    }

    override suspend  fun releaseCharactersData() {
        localDataSource.releaseAllCharacterData()
    }

}