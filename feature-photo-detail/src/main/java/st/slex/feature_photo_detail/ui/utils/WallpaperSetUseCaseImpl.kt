package st.slex.feature_photo_detail.ui.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget

class WallpaperSetUseCaseImpl(
    private val context: Context,
    private val target: CustomTarget<Bitmap>
) : WallpaperSetUseCase {

    override operator fun invoke(url: String) {
        Glide.with(context)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .load(Uri.parse(url))
            .into(target)
    }
}