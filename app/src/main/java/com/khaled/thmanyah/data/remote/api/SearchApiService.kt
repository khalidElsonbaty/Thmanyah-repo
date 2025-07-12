package com.khaled.thmanyah.data.remote.api

import com.khaled.thmanyah.data.remote.dto.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface SearchApiService {
    @GET()
    suspend fun getSearchSections(@Url url:String
    ): HomeResponse
}