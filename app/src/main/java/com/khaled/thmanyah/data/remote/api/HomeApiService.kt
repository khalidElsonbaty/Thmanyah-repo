package com.khaled.thmanyah.data.remote.api

import com.khaled.thmanyah.data.remote.dto.HomeResponse
import retrofit2.http.GET

interface HomeApiService {
    @GET("https://api-v2-b2sit6oh3a-uc.a.run.app/home_sections")
    suspend fun getHomeSections(): HomeResponse
}