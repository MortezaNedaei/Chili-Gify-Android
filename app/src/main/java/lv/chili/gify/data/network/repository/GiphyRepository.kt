package lv.chili.gify.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import lv.chili.gify.data.model.GifData
import lv.chili.gify.data.network.api.GiphyApiService
import lv.chili.gify.data.network.paging.GiphyPagingSource
import lv.chili.gify.data.network.paging.GiphyTrendingPagingSource
import javax.inject.Inject

class GiphyRepository @Inject constructor(
    private val apiService: GiphyApiService
) {

    fun getSearchFlow(query: String): Flow<PagingData<GifData>> {
        return Pager(
            config = PagingConfig(pageSize = 25, enablePlaceholders = false),
            pagingSourceFactory = {
                GiphyPagingSource(
                    apiService = apiService,
                    query = query
                )
            }
        ).flow
    }

    fun getTrendingFlow(): Flow<PagingData<GifData>> {
        return Pager(
            config = PagingConfig(pageSize = 25, enablePlaceholders = false),
            pagingSourceFactory = {
                GiphyTrendingPagingSource(
                    apiService = apiService
                )
            }
        ).flow
    }
}