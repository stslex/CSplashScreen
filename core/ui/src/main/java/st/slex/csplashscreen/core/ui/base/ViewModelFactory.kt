package st.slex.csplashscreen.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = viewModelMap
        .getOrElse(modelClass) {
            viewModelMap.firstNotNullOfOrNull { entry ->
                entry.takeIf {
                    modelClass.isAssignableFrom(it.key)
                }?.value
            }
        }
        ?.get() as? T?
        ?: throw IllegalArgumentException("Unknown model class $modelClass")
}