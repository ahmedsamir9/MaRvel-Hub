package com.example.marvelhub.data.local.doa

import androidx.paging.PagingSource
import androidx.room.*
import com.example.marvelhub.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characters:List<CharacterEntity>)
    @Query("select * from Character limit 20 offset :offsetNumber" )
    fun getCharacters(offsetNumber:Int): List<CharacterEntity>
    @Query ("select * from character where characterId =:id")
    fun getCharacter(id :Int): Flow<CharacterEntity>
}