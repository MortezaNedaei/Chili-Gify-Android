package lv.chili.gify.data.network.paging

import androidx.paging.PagingSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import lv.chili.gify.data.model.GifData
import lv.chili.gify.data.model.GiphySearchResponse
import lv.chili.gify.data.model.ImageContainer
import lv.chili.gify.data.model.ImageDetails
import lv.chili.gify.data.model.PaginationData
import lv.chili.gify.data.network.api.GiphyApiService
import org.junit.Assert.assertEquals
import org.junit.Test

class GiphyPagingSourceTest {

    private val apiService: GiphyApiService = mockk()
    private val query = "cats"

    @Test
    fun `load returns page when api succeeds`() = runTest {
        val giphyPagingSource = GiphyPagingSource(apiService, query)
        val gifs = listOf(createTestGif())
        val response = GiphySearchResponse(gifs, PaginationData(1, 1, 0))
        coEvery { apiService.searchGifs(any(), any(), any(), any()) } returns response

        val result = giphyPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        val expected = PagingSource.LoadResult.Page(
            data = gifs,
            prevKey = null,
            nextKey = 20
        )
        assertEquals(expected, result)
    }

    @Test
    fun `load returns error when api fails`() = runTest {
        val giphyPagingSource = GiphyPagingSource(apiService, query)
        val exception = RuntimeException("Test exception")
        coEvery { apiService.searchGifs(any(), any(), any(), any()) } throws exception

        val result = giphyPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        assertEquals(PagingSource.LoadResult.Error<Int, GifData>(exception), result)
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
                height = "200",
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