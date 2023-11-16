package st.slex.csplashscreen.feature.home.ui.component.tabs

import androidx.annotation.StringRes
import st.slex.csplashscreen.feature.home.R

enum class MainScreenTabs(
    val pageNum: Int,
    @StringRes val titleRes: Int,
) {
    PHOTOS(
        pageNum = 0,
        titleRes = R.string.main_screen_tab_photos
    ),
    COLLECTIONS(
        pageNum = 1,
        titleRes = R.string.main_screen_tab_collections
    )
}
