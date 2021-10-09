package com.example.marvelhub.data.repository



import androidx.paging.*
import com.example.marvelhub.data.local.mappers.CharacterDomainLocalMapper
import com.example.marvelhub.data.pagingremoteMedatiors.CharacterRemoteMediator
import com.example.marvelhub.data.remote.mapper.CharacterEntityDtoMapper
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.*


class CharacterRepositoryImpl (private val localDataSource: LocalDataSource,private val remoteDataSource: RemoteDataSource): CharacterRepository {
  private val mapper = CharacterDomainLocalMapper()
    val characterEntityDtoMapper= CharacterEntityDtoMapper()
    private var dataBaseOffset = 0
    @ExperimentalPagingApi
    override suspend fun getCharacters(): Flow<PagingData<Character>> {
      return  Pager(
        config =  PagingConfig(
          pageSize = 10,
          enablePlaceholders = false) ,
        remoteMediator = CharacterRemoteMediator(localDataSource,remoteDataSource),
        pagingSourceFactory = {localDataSource.getCharacters()}
      ).flow.map { paging ->
        paging.map { entity -> mapper.from(entity) }
      }
    }

  override suspend fun getCharacterById(id: Int): Character {
      return mapper.from(localDataSource.getCharacterDataById(id))
  }

}