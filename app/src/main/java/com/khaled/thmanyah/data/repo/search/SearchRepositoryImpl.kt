package com.khaled.thmanyah.data.repo.search

import com.khaled.thmanyah.data.remote.api.SearchApiService
import com.khaled.thmanyah.data.remote.dto.HomeResponse
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApiService: SearchApiService
) : SearchRepository {
    override suspend fun getSearchResults(): HomeResponse = searchApiService.getSearchSections("https://mock.apidog.com/m1/735111-711675-default/search")
}