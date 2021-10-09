package com.example.marvelhub.data.remote.model.eventresponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val eventDtos: List<EventDto>,
    @SerializedName("total")
    val total: Int
)