package lv.chili.gify.ui.page.search

import androidx.paging.PagingData
import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import lv.chili.gify.data.model.GifData
import lv.chili.gify.data.network.repository.GiphyRepository
import lv.chili.gify.util.MainCoroutineRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository: GiphyRepository = mockk(relaxed = true)
    private lateinit var viewModel: SearchViewModel

    @Test
    fun `query change triggers search`() = runTest {
        val query = "cats"
        val searchPagingData = PagingData.from(listOf<GifData>())
        val trendingPagingData = PagingData.from(listOf<GifData>())
        every { repository.getSearchFlow(query) } returns flowOf(searchPagingData)
        every { repository.getTrendingFlow() } returns flowOf(trendingPagingData)

        viewModel = SearchViewModel(repository)
        viewModel.searchResults.test {
            awaitItem() 
            viewModel.onQueryChanged(query)
            assertEquals(query, viewModel.searchQuery.value)
            awaitItem()
            verify { repository.getSearchFlow(query) }
        }
    }

    @Test
    fun `blank query triggers trending`() = runTest {
        val pagingData = PagingData.from(listOf<GifData>())
        every { repository.getTrendingFlow() } returns flowOf(pagingData)
        
        viewModel = SearchViewModel(repository)

        viewModel.searchResults.test {
            awaitItem()
            verify { repository.getTrendingFlow() }
        }
    }
}