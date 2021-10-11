package com.example.marvelhub.data.remote.model.seriesresponse


import com.example.marvelhub.data.remote.model.storiesresponse.ItemXX
import com.google.gson.annotations.SerializedName

data class Creators(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemXX>,
    @SerializedName("returned")
    val returned: Int
)