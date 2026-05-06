package com.chordbay.app.data.repository.remote

import com.chordbay.app.data.mappers.toSong
import com.chordbay.app.data.model.Song
import com.chordbay.app.data.remote.ChordsBayApiService
import org.jsoup.Jsoup
import java.io.IOException

class SongRemoteRepositoryImpl(
    private val apiService: ChordsBayApiService
) : SongRemoteRepository {

    override suspend fun searchSongs(query: String?, field: Any, offset: Int, limit: Int): Result<List<Song>> {
        return try {
            val response = apiService.searchSongs(query ?: "")
            if (response.isSuccessful) {
                val searchItems = response.body()?.items ?: emptyList()
                val songs = searchItems.map { item ->
                    // FreeShow-style Scrape: Fetch full content from the result link
                    val scrapedContent = scrapeLyrics(item.link) 
                    Song(
                        title = item.title,
                        content = scrapedContent,
                        artist = "Search Result"
                    )
                }
                Result.success(songs)
            } else {
                Result.failure(IOException("Search failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun scrapeLyrics(url: String): String {
        return try {
            // Logic to target Christian lyric sites (e.g., Ultimate Guitar, PraiseCharts)
            val doc = Jsoup.connect(url).get()
            // Example selector for lyrics - this varies by site
            doc.select("pre, .lyrics, .chord-content").text() 
        } catch (e: Exception) {
            "Content could not be loaded."
        }
    }
    
    // Other override methods (getSongs, createSong, etc.) should be cleared or adapted 
    // if the chordbay.eu backend is no longer used.
}
