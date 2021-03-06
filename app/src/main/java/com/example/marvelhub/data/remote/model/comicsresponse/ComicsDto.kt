package com.example.marvelhub.data.remote.model.comicsresponse


import com.example.marvelhub.data.remote.model.characterresponse.Thumbnail
import com.example.marvelhub.data.remote.model.seriesresponse.Characters
import com.example.marvelhub.data.remote.model.seriesresponse.Creators
import com.example.marvelhub.data.remote.model.seriesresponse.Url
import com.example.marvelhub.data.remote.model.storiesresponse.Events
import com.google.gson.annotations.SerializedName

data class ComicsDto(
    @SerializedName("characters")
    val characters: Characters,
    @SerializedName("collectedIssues")
    val collectedIssues: List<Any>,
    @SerializedName("collections")
    val collections: List<Any>,
    @SerializedName("creators")
    val creators: Creators,
    @SerializedName("dates")
    val dates: List<Date>,
    @SerializedName("description")
    val description: Any,
    @SerializedName("diamondCode")
    val diamondCode: String,
    @SerializedName("digitalId")
    val digitalId: Int,
    @SerializedName("ean")
    val ean: String,
    @SerializedName("events")
    val events: Events,
    @SerializedName("format")
    val format: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("issn")
    val issn: String,
    @SerializedName("issueNumber")
    val issueNumber: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("prices")
    val prices: List<Price>,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("series")
    val series: Series,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("textObjects")
    val textObjects: List<TextObject>,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("title")
    val title: String,
    @SerializedName("upc")
    val upc: String,
    @SerializedName("urls")
    val urls: List<Url>,
    @SerializedName("variantDescription")
    val variantDescription: String,
    @SerializedName("variants")
    val variants: List<Variant>
)