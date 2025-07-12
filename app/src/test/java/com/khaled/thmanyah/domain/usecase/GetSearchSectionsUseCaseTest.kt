package com.khaled.thmanyah.domain.usecase

import com.khaled.thmanyah.data.repo.search.SearchRepository
import com.khaled.thmanyah.domain.mapper.SectionsMapper
import com.khaled.thmanyah.data.remote.dto.HomeResponse
import com.khaled.thmanyah.domain.model.HomeViewState
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class GetSearchSectionsUseCaseTest {

    private lateinit var repository: SearchRepository
    private lateinit var mapper: SectionsMapper
    private lateinit var useCase: GetSearchSectionsUseCase

    @Before
    fun setup() {
        repository = mock()
        mapper = mock()
        useCase = GetSearchSectionsUseCase(repository, mapper)
    }

    @Test
    fun `invoke should return mapped HomeViewState from search`() = runTest {
        // Arrange
        val query = "android"
        val fakeResponse = HomeResponse(sections = emptyList())
        val expectedViewState = HomeViewState(sections = emptyList())

        whenever(repository.getSearchResults()).thenReturn(fakeResponse)
        whenever(mapper.map(fakeResponse)).thenReturn(expectedViewState)

        // Act
        val result = useCase(query)

        // Assert
        assert(result == expectedViewState)
        verify(repository).getSearchResults()
        verify(mapper).map(fakeResponse)
    }
}