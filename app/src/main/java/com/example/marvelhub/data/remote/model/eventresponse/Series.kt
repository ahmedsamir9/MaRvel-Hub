package com.example.marvelhub.data.remote.model.eventresponse

import com.example.marvelhub.data.remote.model.storiesresponse.ItemXX
import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemXX>,
    @SerializedName("returned")
    val returned: Int
)