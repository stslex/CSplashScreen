package st.slex.feature_image_raw.ui

import androidx.lifecycle.ViewModel
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen

class RawImageViewModel(
    private val args: AppArguments.RawImageScreen,
    private val navigate: (NavigationScreen) -> Unit
) : ViewModel() {

    val url: String
        get() = args.url

    fun popBackStack() {
        navigate(NavigationScreen.PopBackStack)
    }
}