package lv.chili.gify.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single GIF object.
 * This is the main model you'll use in your PagingSource and UI.
 */
@Serializable
data class GifData(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("images")
    val images: ImageContainer
)

/**
 * A container holding the different versions of the GIF.
 * We only care about 'fixed_width' (for the grid) and 'original' (for detail).
 */
@Serializable
data class ImageContainer(
    @SerialName("fixed_width")
    val fixedWidth: ImageDetails,
    @SerialName("original")
    val original: ImageDetails
)

/**
 * Represents the details for a specific GIF format (like URL, width, height).
 */
@Serializable
data class ImageDetails(
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: String,
    @SerialName("height")
    val height: String
)