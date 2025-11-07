package lv.chili.gify.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.io.IOException

/**
 * A reusable Composable to display a user-friendly error message
 * and an optional retry button.
 *
 * @param error The exception that occurred.
 * @param modifier The Modifier.
 * @param onRetry A lambda function to be called when the retry button is clicked.
 */
@Composable
fun ErrorMessage(
    error: Throwable,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {},
) {
    val errorMessage = when (error) {
        is IOException -> "Network error. Please check your connection."
        else -> "An unexpected error occurred: ${error.message}"
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "😢",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}