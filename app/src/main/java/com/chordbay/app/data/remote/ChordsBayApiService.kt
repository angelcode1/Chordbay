package com.chordbay.app.data.remote

import com.chordbay.app.data.remote.model.SongDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChordsBayApiService {
    // New endpoint for Google Custom Search or Scraping Proxy
    @GET("./")
    suspend fun searchSongs(
        @Query("q") query: String,
        @Query("key") apiKey: String = "YOUR_GOOGLE_API_KEY",
        @Query("cx") engineId: String = "YOUR_SEARCH_ENGINE_ID",
        @Query("num") limit: Int = 10
    ): Response<GoogleSearchResponse> // You must create this DTO based on Google's JSON
}
