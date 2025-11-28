package lv.chili.gify.ui.page.search

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import coil.ImageLoader
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import lv.chili.gify.data.model.GifData
import lv.chili.gify.data.model.ImageContainer
import lv.chili.gify.data.model.ImageDetails
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel: SearchViewModel = mockk(relaxed = true)

    @Test
    fun searchScreen_displaysGifs_handlesClicks() {
        val gif = createTestGif()
        val gifs = flowOf(PagingData.from(listOf(gif)))
        val query = MutableStateFlow("")

        every { viewModel.searchResults } returns gifs
        every { viewModel.searchQuery } returns query

        val onGifClick: (GifData) -> Unit = mockk(relaxed = true)
        val imageLoader: ImageLoader = mockk(relaxed = true)

        composeTestRule.setContent {
            SearchScreen(
                viewModel = viewModel,
                imageLoader = imageLoader,
                onGifClick = onGifClick
            )
        }

        composeTestRule.onNodeWithText(gif.title).assertExists()
        composeTestRule.onNodeWithText(gif.title).performClick()

        verify { onGifClick(gif) }
    }

    private fun createTestGif() = GifData(
        id = "1",
        title = "Test GIF",
        images = ImageContainer(
            fixedWidth = ImageDetails(
                url = "http://example.com/gif",
                width = "200",
                height = "200"
            ),
            original = ImageDetails(
                url = "http://example.com/gif",
                width = "200",
                height = "200"
            ),
            fixedWidthDownsampled = ImageDetails(
                url = "http://example.com/gif",
                width = "200",
                height = "200"
            ),
            fixedWidthStill = ImageDetails(
                url = "http://example.com/gif",
                width = "200",
                height = "200"
            )
        )
    )
}
