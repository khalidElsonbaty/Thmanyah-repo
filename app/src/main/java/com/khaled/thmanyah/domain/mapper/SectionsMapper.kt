package com.khaled.thmanyah.domain.mapper

import com.khaled.thmanyah.data.remote.dto.HomeResponse
import com.khaled.thmanyah.domain.model.ContentItemUiModel
import com.khaled.thmanyah.domain.model.HomeViewState
import com.khaled.thmanyah.domain.model.SectionUiModel
import javax.inject.Inject

class SectionsMapper @Inject constructor() {

    fun map(response: HomeResponse): HomeViewState {
        val sections = response.sections.sortedBy { it.order }.map { section ->
            SectionUiModel(
                name = section.name,
                type = section.type,
                contentType = section.contentType,
                content = section.content.map { item ->
                    ContentItemUiModel(
                        id = item.id,
                        name = item.name,
                        imageUrl = item.avatarUrl.orEmpty(),
                        description = item.description.orEmpty(),
                        contentType = section.contentType
                    )
                }
            )
        }
        return HomeViewState(sections)
    }
}