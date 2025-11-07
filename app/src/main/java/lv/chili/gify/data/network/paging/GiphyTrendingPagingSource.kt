package lv.chili.gify.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import lv.chili.gify.BuildConfig
import lv.chili.gify.data.model.GifData
import lv.chili.gify.data.network.api.GiphyApiService

class GiphyTrendingPagingSource(
    private val apiService: GiphyApiService,
) : PagingSource<Int, GifData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifData> {
        return try {
            val offset = params.key ?: 0
            val response = apiService.getTrendingGifs(
                apiKey = BuildConfig.GIPHY_API_KEY,
                limit = params.loadSize,
                offset = offset
            )
            val gifs = response.data

            LoadResult.Page(
                data = gifs,
                prevKey = if (offset == 0) null else offset - params.loadSize,
                nextKey = if (gifs.isEmpty()) null else offset + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(state.config.pageSize)
                ?: anchorPage?.nextKey?.minus(state.config.pageSize)
        }
    }
}