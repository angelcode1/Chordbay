package com.chordbay.app.data.mappers

import com.chordbay.app.data.model.Song
import com.chordbay.app.data.model.chord.HBFormat
import com.chordbay.app.data.remote.model.SongDto

fun SongDto.toSong(): Song =
    Song(
        remoteId = id ?: sourceUrl, // Use URL as a unique ID for search results
        title = title,
        artist = artist,
        content = content,
        hBFormat = if(germanNotation) HBFormat.GER else HBFormat.ENG
    )
