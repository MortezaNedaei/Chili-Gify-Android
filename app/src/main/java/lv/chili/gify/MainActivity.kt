package lv.chili.gify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import lv.chili.gify.ui.navigation.AppNavGraph
import lv.chili.gify.ui.theme.ChiliGifyAndroidTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChiliGifyAndroidTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    AppNavGraph(
                        navController = navController,
                        imageLoader = imageLoader
                    )
                }
            }
        }
    }
}
