package com.khaled.thmanyah.domain.usecase

import com.khaled.thmanyah.data.repo.home.HomeRepository
import com.khaled.thmanyah.domain.model.HomeViewState
import com.khaled.thmanyah.domain.mapper.SectionsMapper
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val sectionsMapper: SectionsMapper
) {
    suspend operator fun invoke(): HomeViewState {
        val response =repository.getHomeSections()
        return sectionsMapper.map(response)
    }

}