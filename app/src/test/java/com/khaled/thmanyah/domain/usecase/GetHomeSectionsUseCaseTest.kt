package com.khaled.thmanyah.domain.usecase

import com.khaled.thmanyah.data.remote.dto.HomeResponse
import com.khaled.thmanyah.data.repo.home.HomeRepository
import com.khaled.thmanyah.domain.mapper.SectionsMapper
import com.khaled.thmanyah.domain.model.HomeViewState
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class GetHomeSectionsUseCaseTest {

    private lateinit var repository: HomeRepository
    private lateinit var mapper: SectionsMapper
    private lateinit var useCase: GetHomeSectionsUseCase

    @Before
    fun setup() {
        repository = mock()
        mapper = mock()
        useCase = GetHomeSectionsUseCase(repository, mapper)
    }

    @Test
    fun `invoke should return mapped HomeViewState`() = runTest {
        // Arrange
        val fakeResponse = HomeResponse(sections = emptyList())
        val expectedViewState = HomeViewState(sections = emptyList())

        whenever(repository.getHomeSections()).thenReturn(fakeResponse)
        whenever(mapper.map(fakeResponse)).thenReturn(expectedViewState)

        // Act
        val result = useCase()

        // Assert
        assert(result == expectedViewState)
        verify(repository).getHomeSections()
        verify(mapper).map(fakeResponse)
    }
}