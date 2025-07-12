package com.khaled.thmanyah.presentation.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.navigation.compose.rememberNavController
import com.khaled.thmanyah.core.utils.ApiResult
import com.khaled.thmanyah.domain.model.HomeViewState
import com.khaled.thmanyah.domain.model.SectionUiModel
import dagger.hilt.android.testing.HiltAndroidTest
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.khaled.thmanyah.domain.model.ContentItemUiModel
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule() // Your launch Activity


    @Test
    fun homeScreen_showsErrorView() {
        val fakeViewModel = FakeHomeViewModel(ApiResult.Error(Exception("No Internet")))

        composeTestRule.setContent {
            HomeScreen(
                navController = rememberNavController(),
                viewModel = fakeViewModel
            )
        }

        composeTestRule
            .onNodeWithText("Error: No Internet")
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_showsSections() {
        val mockContentList = listOf(
            ContentItemUiModel(
                id = "1",
                name = "Mock Podcast Episode 1",
                imageUrl = "https://media.npr.org/assets/img/2022/09/22/consider-this_tile_npr-network-01_sq-96ca581b062bc4641008f69cf1e071394ce4c611.jpg?s=1400&c=66&f=jpg",
                description = "A description for mock episode 1",
                contentType = "podcast"
            ),
            ContentItemUiModel(
                id = "2",
                name = "Mock Podcast Episode 2",
                imageUrl = "https://media.npr.org/assets/img/2018/08/03/npr_tbl_podcasttile_sq-284e5618e2b2df0f3158b076d5bc44751e78e1b6.jpg?s=1400&c=66&f=jpg",
                description = "A description for mock episode 2",
                contentType = "podcast"
            ),
            ContentItemUiModel(
                id = "3",
                name = "Mock Podcast Episode 3",
                imageUrl = "https://media.npr.org/assets/img/2023/07/25/bsbt_tile-art-1_sq-356cd45eb06714adc134a3f2860343d68551c53c.png?s=1400&c=66&f=png",
                description = "A description for mock episode 3",
                contentType = "podcast"
            ),
            ContentItemUiModel(
                id = "4",
                name = "Mock Podcast Episode 4",
                imageUrl = "https://media.npr.org/assets/img/2018/08/03/npr_tbl_podcasttile_sq-284e5618e2b2df0f3158b076d5bc44751e78e1b6.jpg?s=1400&c=66&f=jpg",
                description = "A description for mock episode 4",
                contentType = "podcast"
            )

        )

        val section = SectionUiModel(
            name = "Popular",
            type = "square",
            contentType = "podcast",
            content = mockContentList
        )
        val fakeState = HomeViewState(sections = listOf(section))
        val fakeViewModel = FakeHomeViewModel(ApiResult.Success(fakeState))

        composeTestRule.setContent {
            HomeScreen(
                navController = rememberNavController(),
                viewModel = fakeViewModel
            )
        }

        composeTestRule
            .onNodeWithText("Popular")
            .assertIsDisplayed()
    }
}
