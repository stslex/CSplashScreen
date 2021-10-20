package st.slex.csplashscreen.ui.screens.raw_image

import androidx.lifecycle.ViewModel
import st.slex.csplashscreen.ui.navigation.Navigator
import javax.inject.Inject

class RawImageViewModel @Inject constructor(
    private val _navigator: Navigator
) : ViewModel() {

    val navigator: Navigator = _navigator
}