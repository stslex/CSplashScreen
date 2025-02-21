package st.slex.csplashscreen.feature.photo_detail.ui.utils

import android.app.WallpaperManager
import android.content.Context
import coil3.SingletonImageLoader
import coil3.toBitmap
import st.slex.scplashscreen.core.image.AppImageRequest

class WallpaperSetUseCaseImpl(
    private val context: Context,
) : WallpaperSetUseCase {

    override operator fun invoke(url: String) {
        val request = AppImageRequest
            .createImageRequestBuilder(context, url)
            .listener { _, result ->
                WallpaperManager
                    .getInstance(context)
                    .setBitmap(result.image.toBitmap())
            }
            .build()
        SingletonImageLoader.get(context).enqueue(request)
    }
}