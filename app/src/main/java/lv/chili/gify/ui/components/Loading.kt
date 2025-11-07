package lv.chili.gify.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A reusable Composable to display a user-friendly loading UI
 *
 * @param modifier The Modifier.
 * @param size The DP value for size of loading progress.
 * @param color The progress color.
 */
@Composable
fun Loading(
    modifier: Modifier = Modifier,
    size: Dp = 4.dp,
    color: Color = ProgressIndicatorDefaults.circularColor,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size),
            color = color
        )
    }
}