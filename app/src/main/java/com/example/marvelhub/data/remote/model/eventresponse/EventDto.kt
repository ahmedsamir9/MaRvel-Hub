package com.example.marvelhub.data.remote.model.eventresponse


import com.example.marvelhub.data.remote.model.characterresponse.Item
import com.example.marvelhub.data.remote.model.characterresponse.Thumbnail
import com.example.marvelhub.data.remote.model.seriesresponse.Characters
import com.example.marvelhub.data.remote.model.seriesresponse.Comics
import com.example.marvelhub.data.remote.model.seriesresponse.Creators
import com.example.marvelhub.data.remote.model.seriesresponse.Url
import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("characters")
    val characters: Characters,
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("creators")
    val creators: Creators,
    @SerializedName("description")
    val description: String,
    @SerializedName("end")
    val end: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("next")
    val next: Item,
    @SerializedName("previous")
    val previous: Item,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("series")
    val series: Series,
    @SerializedName("start")
    val start: String,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("title")
    val title: String,
    @SerializedName("urls")
    val urls: List<Url>
)