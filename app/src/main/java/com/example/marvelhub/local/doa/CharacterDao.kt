package com.example.marvelhub.local.doa

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelhub.local.model.CharacterEntity


@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(movies:List<CharacterEntity>)
    @Query("select * from Character" )
    fun getCharacters(): PagingSource<Int, CharacterEntity>
    @Query ("select * from character where id =:characterId")
    fun getCharacter(characterId :Int):CharacterEntity

}