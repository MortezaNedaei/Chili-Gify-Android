package lv.chili.gify.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import lv.chili.gify.ui.page.detail.DetailScreen
import lv.chili.gify.ui.page.search.SearchScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Defines the navigation routes in the app.
 */
object AppRoutes {
    const val SEARCH = "search"
    const val DETAIL_ROUTE = "detail"
    const val ARG_GIF_URL = "gifUrl"
    const val ARG_GIF_TITLE = "gifTitle"

    const val DETAIL_SCREEN_ROUTE = "$DETAIL_ROUTE/{$ARG_GIF_URL}/{$ARG_GIF_TITLE}"
}

/**
 * Sets up the main navigation graph for the application.
 */
@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AppRoutes.SEARCH
    ) {
        composable(route = AppRoutes.SEARCH) {
            SearchScreen(
                onGifClick = { gifData ->
                    val encodedUrl = URLEncoder.encode(
                        gifData.images.original.url,
                        StandardCharsets.UTF_8.toString()
                    )
                    val encodedTitle = URLEncoder.encode(
                        gifData.title,
                        StandardCharsets.UTF_8.toString()
                    )

                    navController.navigate("${AppRoutes.DETAIL_ROUTE}/$encodedUrl/$encodedTitle")
                }
            )
        }
        composable(
            route = AppRoutes.DETAIL_SCREEN_ROUTE,
            arguments = listOf(
                navArgument(AppRoutes.ARG_GIF_URL) { type = NavType.StringType },
                navArgument(AppRoutes.ARG_GIF_TITLE) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString(AppRoutes.ARG_GIF_URL) ?: ""
            val encodedTitle = backStackEntry.arguments?.getString(AppRoutes.ARG_GIF_TITLE) ?: ""

            val gifUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
            val gifTitle = URLDecoder.decode(encodedTitle, StandardCharsets.UTF_8.toString())

            DetailScreen(
                gifUrl = gifUrl,
                title = gifTitle,
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }
    }
}