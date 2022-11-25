package st.slex.feature_photo_detail.ui.download

fun interface DownloadImageUseCase {
    operator fun invoke(url: String, fileName: String)
}