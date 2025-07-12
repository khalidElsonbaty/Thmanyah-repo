package com.khaled.thmanyah.data.repo.home

import com.khaled.thmanyah.data.remote.dto.HomeResponse

interface HomeRepository {
        suspend fun getHomeSections(): HomeResponse
}