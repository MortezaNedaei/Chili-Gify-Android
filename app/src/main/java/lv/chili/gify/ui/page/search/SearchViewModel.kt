package lv.chili.gify.ui.page.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import lv.chili.gify.data.model.GifData
import lv.chili.gify.data.network.repository.GiphyRepository
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GiphyRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val searchResults: Flow<PagingData<GifData>> = _searchQuery
        .debounce(500L)
        .flatMapLatest { query ->
            if (query.isNotBlank()) {
                repository.getSearchFlow(query)
            } else {
                repository.getTrendingFlow()
            }
        }
        .cachedIn(viewModelScope)

    fun onQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }
}