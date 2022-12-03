package st.slex.feature_photo_detail.ui.utils

fun interface DownloadImageUseCase {
    operator fun invoke(url: String, fileName: String)
}