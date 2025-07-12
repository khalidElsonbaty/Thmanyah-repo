package com.khaled.thmanyah.data.repo.home

import com.khaled.thmanyah.data.remote.api.HomeApiService
import com.khaled.thmanyah.data.remote.dto.HomeResponse
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApiService: HomeApiService
) : HomeRepository {
    override suspend fun getHomeSections(): HomeResponse = homeApiService.getHomeSections()
}