package com.example.marvelhub.data.remote.model.seriesresponse


import com.google.gson.annotations.SerializedName

data class Next(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)