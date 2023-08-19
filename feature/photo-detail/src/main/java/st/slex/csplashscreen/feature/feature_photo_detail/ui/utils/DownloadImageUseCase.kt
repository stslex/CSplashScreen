package st.slex.csplashscreen.feature.feature_photo_detail.ui.utils

fun interface DownloadImageUseCase {
    operator fun invoke(url: String, fileName: String)
}