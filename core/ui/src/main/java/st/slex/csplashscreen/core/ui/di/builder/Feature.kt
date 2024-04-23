package st.slex.csplashscreen.core.ui.di.builder

import androidx.lifecycle.ViewModelProvider

interface Feature {

    /**
     * This property is used to get the ViewModelFactory of the feature
     * @return ViewModelProvider.Factory
     * */
    val viewModelFactory: ViewModelProvider.Factory
}
