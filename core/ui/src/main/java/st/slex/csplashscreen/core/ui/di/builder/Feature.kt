package st.slex.csplashscreen.core.ui.di.builder

import androidx.lifecycle.ViewModelProvider

interface Feature {

    val viewModelFactory: ViewModelProvider.Factory
}
