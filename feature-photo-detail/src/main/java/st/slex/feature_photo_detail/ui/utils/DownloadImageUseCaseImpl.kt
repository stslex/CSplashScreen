package st.slex.feature_photo_detail.ui.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import st.slex.feature_photo_detail.R
import java.io.File

class DownloadImageUseCaseImpl(
    private val context: Context
) : DownloadImageUseCase {

    private val title = context.getString(R.string.download_title)
    private val description = context.getString(R.string.download_description)
    private val externalDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    override fun invoke(
        url: String,
        fileName: String
    ) {
        val correctFileName = StringBuilder(fileName).append(IMAGE_POSTFIX).toString()
        val file = File(externalDir, correctFileName)
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(title)
            .setDescription("${correctFileName}: $description")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, file.name)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    companion object {
        private const val IMAGE_POSTFIX = ".jpg"
    }
}