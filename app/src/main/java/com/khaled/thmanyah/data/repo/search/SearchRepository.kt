package com.khaled.thmanyah.data.repo.search

import com.khaled.thmanyah.data.remote.dto.HomeResponse

interface SearchRepository {
        suspend fun getSearchResults(): HomeResponse
}