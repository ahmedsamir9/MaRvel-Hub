package com.example.marvelhub.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelhub.local.doa.CharacterDao
import com.example.marvelhub.local.model.CharacterEntity


@Database(entities = arrayOf(CharacterEntity::class),version = 1,exportSchema = false)
abstract class MarvelDataBase: RoomDatabase() {
    abstract fun characterDao():CharacterDao
}