package com.khaled.thmanyah.domain.usecase

import com.khaled.thmanyah.domain.model.HomeViewState
import com.khaled.thmanyah.data.repo.search.SearchRepository
import com.khaled.thmanyah.domain.mapper.SectionsMapper
import javax.inject.Inject

class GetSearchSectionsUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val sectionsMapper: SectionsMapper
) {
    suspend operator fun invoke(query: String): HomeViewState {
        val response =repository.getSearchResults()
        return sectionsMapper.map(response)
    }

}