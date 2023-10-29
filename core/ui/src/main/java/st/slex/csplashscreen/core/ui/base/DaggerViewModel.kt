package st.slex.csplashscreen.core.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder

@Composable
inline fun <reified T : ViewModel> daggerViewModel(
    key: String? = null,
    crossinline factory: () -> ViewModelProvider.Factory
): T = viewModel(
    modelClass = T::class.java,
    key = key,
    factory = factory()
)

@Composable
inline fun <reified VM : ViewModel> setupComponent(
    builder: FeatureBuilder,
    key: String? = null,
): VM {
    val context = LocalContext.current
    return daggerViewModel(key) {
        builder
            .build(context)
            .viewModelFactory
    }
}