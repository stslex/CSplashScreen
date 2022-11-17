package st.slex.feature_photo_detail.ui

import android.annotation.SuppressLint
import android.app.Application
import android.app.DownloadManager
import android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment.DIRECTORY_DOWNLOADS
import st.slex.core.Resource
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface DownloadImageUseCase {

    suspend fun download(url: String, fileName: String = url): Resource<Nothing?>

    class Base @Inject constructor(
        private val application: Application
    ) : DownloadImageUseCase {

        @SuppressLint("Range")
        override suspend fun download(
            url: String,
            fileName: String
        ): Resource<Nothing?> = suspendCoroutine { continuation ->
            val downloadManager = application.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val request = DownloadManager
                .Request(Uri.parse(url))
                .setTitle("Downloading")
                .setDescription("Downloading image...")
                .setDestinationInExternalPublicDir(DIRECTORY_DOWNLOADS, fileName)
                .setNotificationVisibility(VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            downloadManager.enqueue(request)
            val query = DownloadManager.Query()
            query.setFilterByStatus(DownloadManager.STATUS_FAILED)
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL)
            val cursor = downloadManager.query(query)
            if (!cursor.moveToFirst()) return@suspendCoroutine
            when (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                DownloadManager.STATUS_SUCCESSFUL -> {
                    continuation.resumeWith(Result.success(Resource.Success(null)))
                }

                DownloadManager.STATUS_FAILED -> {
                    continuation.resumeWithException(Exception("Failed"))
                }
            }
        }
    }
}