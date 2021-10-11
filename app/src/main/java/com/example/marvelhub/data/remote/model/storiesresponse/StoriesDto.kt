package com.example.marvelhub.data.remote.model.storiesresponse



import com.example.marvelhub.data.remote.model.characterresponse.Thumbnail
import com.example.marvelhub.data.remote.model.seriesresponse.Characters
import com.example.marvelhub.data.remote.model.seriesresponse.Comics
import com.example.marvelhub.data.remote.model.seriesresponse.Creators
import com.google.gson.annotations.SerializedName

data class StoriesDto(
    @SerializedName("characters")
    val characters: Characters,
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("creators")
    val creators: Creators,
    @SerializedName("description")
    val description: String,
    @SerializedName("events")
    val events: Events,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("originalIssue")
    val originalIssue: Item,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("series")
    val series: Series,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
)