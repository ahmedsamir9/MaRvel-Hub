package com.example.marvelhub.data.remote.model.eventresponse


import com.google.gson.annotations.SerializedName

data class Previous(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)