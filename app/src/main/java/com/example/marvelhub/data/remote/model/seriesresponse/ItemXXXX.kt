package com.example.marvelhub.data.remote.model.seriesresponse


import com.google.gson.annotations.SerializedName

data class ItemXXXX(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("type")
    val type: String
)