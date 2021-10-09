package com.example.marvelhub.data.remote.model.eventresponse


import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemXXX>,
    @SerializedName("returned")
    val returned: Int
)