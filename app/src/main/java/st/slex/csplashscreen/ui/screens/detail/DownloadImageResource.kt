package st.slex.csplashscreen.ui.screens.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
import android.content.Context.DOWNLOAD_SERVICE
import android.database.Cursor
import android.net.Uri
import android.os.Environment.DIRECTORY_DOWNLOADS
import st.slex.csplashscreen.core.Resource
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface DownloadImageResource {

    suspend fun download(url: String, fileName: String): Resource<Nothing?>

    class Base @Inject constructor(
        private val activity: Activity
    ) : DownloadImageResource {

        @SuppressLint("Range")
        override suspend fun download(
            url: String, fileName: String
        ): Resource<Nothing?> = suspendCoroutine { continuation ->
            val downloadManager = activity.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val request = DownloadManager
                .Request(Uri.parse(url))
                .setTitle("Downloading")
                .setDescription("Downloading image...")
                .setDestinationInExternalFilesDir(activity, DIRECTORY_DOWNLOADS, fileName)
                .setNotificationVisibility(VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            downloadManager.enqueue(request)
            var cursor: Cursor? = null
            val query = DownloadManager.Query()
            query.setFilterByStatus(DownloadManager.STATUS_FAILED)
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL)
            cursor = downloadManager.query(query)
            if (cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                when (status) {
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
}