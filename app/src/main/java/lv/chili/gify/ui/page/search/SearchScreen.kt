package lv.chili.gify.ui.page.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import lv.chili.gify.R
import lv.chili.gify.data.model.GifData
import lv.chili.gify.ui.components.ErrorMessage
import lv.chili.gify.ui.components.Loading

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    imageLoader: ImageLoader,
    onGifClick: (GifData) -> Unit
) {
    val query by viewModel.searchQuery.collectAsStateWithLifecycle()
    val lazyGifItems = viewModel.searchResults.collectAsLazyPagingItems()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TextField(
                value = query,
                onValueChange = { viewModel.onQueryChanged(it) },
                placeholder = { Text(stringResource(R.string.search_gify)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                content = {
                    items(
                        count = lazyGifItems.itemCount,
                        key = lazyGifItems.itemKey { it.uniqueId },
                        contentType = lazyGifItems.itemContentType { "GifGridItem" }
                    ) { index ->
                        val gif = lazyGifItems[index]
                        if (gif != null) {
                            GifGridItem(
                                gif = gif,
                                imageLoader = imageLoader,
                                onClick = { onGifClick(gif) }
                            )
                        }
                    }
                    lazyGifItems.loadState.apply {
                        when {
                            refresh is LoadState.Loading -> {
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    Loading(
                                        modifier = Modifier.padding(16.dp),
                                        size = 42.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }

                            append is LoadState.Loading -> {
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    Loading(
                                        modifier = Modifier.padding(16.dp),
                                        size = 42.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }

                            refresh is LoadState.Error -> {
                                val e = lazyGifItems.loadState.refresh as LoadState.Error
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    ErrorMessage(
                                        error = e.error,
                                        onRetry = lazyGifItems::retry
                                    )
                                }
                            }

                            append is LoadState.Error -> {
                                val e = lazyGifItems.loadState.append as LoadState.Error
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    ErrorMessage(
                                        error = e.error,
                                        onRetry = lazyGifItems::retry
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun GifGridItem(gif: GifData, imageLoader: ImageLoader, onClick: () -> Unit) {
    val imageUrl = gif.images.fixedWidthStill.url
    // we can also use the following urls
    // val imageUrl = gif.images.fixedWidthDownsampled.webp ?: gif.images.fixedWidthDownsampled.url

    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = gif.title,
        contentScale = ContentScale.Fit,
        imageLoader = imageLoader,
        loading = {
            Loading(
                modifier = Modifier.padding(16.dp),
                size = 16.dp,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        error = {},
        modifier = Modifier.clickable { onClick() }
    )
}
