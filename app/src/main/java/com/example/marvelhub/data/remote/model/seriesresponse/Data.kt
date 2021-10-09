package com.example.marvelhub.data.remote.model.seriesresponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val seriesDtos: List<SeriesDto>,
    @SerializedName("total")
    val total: Int
)