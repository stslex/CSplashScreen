package st.slex.feature_photo_detail.ui.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import st.slex.feature_photo_detail.R

class DownloadImageUseCaseImpl(
    private val context: Context
) : DownloadImageUseCase {

    private val title = context.getString(R.string.download_title)
    private val description = context.getString(R.string.download_description)

    override fun invoke(
        url: String,
        fileName: String
    ) {
        val correctFileName = StringBuilder(fileName)
            .append(IMAGE_POSTFIX)
            .toString()
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(title)
            .setDescription("${correctFileName}: $description")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, correctFileName)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    companion object {
        private const val IMAGE_POSTFIX = ".jpg"
    }
}