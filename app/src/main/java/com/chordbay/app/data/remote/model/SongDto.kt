package com.chordbay.app.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongDto(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "title")
    val title: String,
    @Json(name = "artist")
    val artist: String = "Unknown",
    @Json(name = "content")
    var content: String = "", // Will be populated after scraping the 'link'
    @Json(name = "link")
    val sourceUrl: String? = null, 
    @Json(name = "germanNotation")
    val germanNotation: Boolean = false
)
