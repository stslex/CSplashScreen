package st.slex.feature_image_raw.ui

import androidx.lifecycle.ViewModel
import st.slex.core_navigation.testing.AppArguments

class RawImageViewModel(
    private val args: AppArguments.RawImageScreen
) : ViewModel() {

    val url: String
        get() = args.url

    fun popBackStack() {

    }
}