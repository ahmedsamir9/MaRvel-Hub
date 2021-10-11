package com.example.marvelhub.data.local.doa

import androidx.paging.PagingSource
import androidx.room.*
import com.example.marvelhub.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characters:List<CharacterEntity>)
    @Query("select * from Character" )
    fun getCharacters(): PagingSource<Int,CharacterEntity>
    @Query ("select * from character where characterId =:id")
    fun getCharacter(id :Int): Flow<CharacterEntity>
    @Query("select * from character where characterId =:id")
    fun getCharacterById(id: Int):CharacterEntity
    @Query("select * from Character where characterName like '%' || :name|| '%'" )
    fun getCharactersByName(name:String): PagingSource<Int,CharacterEntity>
    @Query("delete from character")
     fun deleteAllCharacters()
}