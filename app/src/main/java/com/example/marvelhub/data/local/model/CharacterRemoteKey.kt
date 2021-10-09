package com.example.marvelhub.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class CharacterRemoteKey (
    @PrimaryKey(autoGenerate = true)
    val pk : Int,
    val afterIndex:Int?,
    val beforeIndex:Int?,
)