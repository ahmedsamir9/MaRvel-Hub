package com.example.marvelhub.data.local.doa

import androidx.paging.PagingSource
import androidx.room.*
import com.example.marvelhub.data.local.model.CharacterEntity
import com.example.marvelhub.data.local.model.CharacterRemoteKey
import kotlinx.coroutines.flow.Flow


@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characters:List<CharacterEntity>)
    @Query("select * from Character" )
    fun getCharacters(): PagingSource<Int,CharacterEntity>
    @Query ("select * from character where characterId =:id")
    fun getCharacter(id :Int): Flow<CharacterEntity>
    @Insert
    suspend fun insertKeys(key:CharacterRemoteKey)
    @Query("select * from characterremotekey  order by pk Desc " )
    fun getCharacterKeys():List<CharacterRemoteKey>
    @Query("select * from character where characterId =:id")
    fun getCharacterById(id: Int):CharacterEntity
}