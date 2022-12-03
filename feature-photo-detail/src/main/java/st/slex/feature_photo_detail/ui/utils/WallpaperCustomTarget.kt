package st.slex.feature_photo_detail.ui.utils

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class WallpaperCustomTarget(
    private val context: Context
) : CustomTarget<Bitmap>() {

    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
        WallpaperManager.getInstance(context).setBitmap(resource)
    }

    override fun onLoadCleared(placeholder: Drawable?) {
        //TODO("Not yet implemented")
    }
}