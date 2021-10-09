package com.example.marvelhub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelhub.data.local.doa.CharacterDao
import com.example.marvelhub.data.local.model.CharacterEntity
import com.example.marvelhub.data.local.model.CharacterRemoteKey


@Database(entities = arrayOf(CharacterEntity::class,CharacterRemoteKey::class),version = 1,exportSchema = false)
abstract class MarvelDataBase: RoomDatabase() {
    abstract fun characterDao():CharacterDao
}