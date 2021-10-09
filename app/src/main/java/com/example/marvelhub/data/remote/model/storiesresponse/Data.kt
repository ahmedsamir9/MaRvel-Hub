package com.example.marvelhub.data.remote.model.storiesresponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val storiesDtos: List<StoriesDto>,
    @SerializedName("total")
    val total: Int
)