package st.slex.scplashscreen.core.image

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest

object AppImageRequest {

    @Composable
    fun createImageRequestBuilder(url: String): ImageRequest.Builder {
        val context = LocalContext.current
        return remember {
            createImageRequestBuilder(context, url)
                .placeholderMemoryCacheKey(url)
        }
    }

    fun createImageRequestBuilder(
        context: Context,
        url: String
    ): ImageRequest.Builder = ImageRequest
        .Builder(context)
        .data(url)
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCacheKey(url)
        .memoryCacheKey(url)
}