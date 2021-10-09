package com.example.marvelhub.data.remote.model.storiesresponse


import com.google.gson.annotations.SerializedName

data class OriginalIssue(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)