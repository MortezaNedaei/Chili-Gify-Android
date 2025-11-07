package lv.chili.gify.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.compose.rememberAsyncImagePainter

@Composable
fun rememberCircularProgressPainter(): Painter {
    val context = LocalContext.current
    val circularProgressDrawable = CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
    return rememberAsyncImagePainter(model = circularProgressDrawable)
}