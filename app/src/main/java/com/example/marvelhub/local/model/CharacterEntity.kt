package com.example.marvelhub.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Character")
data class CharacterEntity (@PrimaryKey val characterId :Int,val characterName :String,val characterDescription:String,val characterImagePath:String)