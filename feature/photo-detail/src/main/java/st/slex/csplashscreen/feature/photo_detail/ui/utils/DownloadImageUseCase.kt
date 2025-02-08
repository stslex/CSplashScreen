package st.slex.csplashscreen.feature.photo_detail.ui.utils

fun interface DownloadImageUseCase {
    operator fun invoke(url: String, fileName: String)
}