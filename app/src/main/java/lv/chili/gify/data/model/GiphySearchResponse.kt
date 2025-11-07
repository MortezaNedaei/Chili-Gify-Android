package lv.chili.gify.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the top-level response from the Giphy API.
 */
@Serializable
data class GiphySearchResponse(
    @SerialName("data")
    val data: List<GifData>,
    @SerialName("pagination")
    val pagination: PaginationData
)

/**
 * Represents the pagination details from the API.
 */
@Serializable
data class PaginationData(
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("count")
    val count: Int,
    @SerialName("offset")
    val offset: Int
)