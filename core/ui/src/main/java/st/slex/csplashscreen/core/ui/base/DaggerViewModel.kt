package st.slex.csplashscreen.core.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.ui.di.MainUiProvider
import st.slex.csplashscreen.core.ui.di.builder.Feature
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
inline fun <reified VM : ViewModel, reified F : Feature> setupComponent(
    builder: FeatureBuilder<F>,
    key: String? = null,
    featureKey: Any? = null
): VM {
    val context = LocalContext.current
    val mainUiApi = checkNotNull(context as? MainUiProvider) {
        "MainUiProvider is not implemented in $context"
    }.api

    DisposableEffect(Unit) {
        builder.setup(
            mainUiApi = mainUiApi,
            key = featureKey
        )
        onDispose {
            builder.clear()
        }
    }

    return daggerViewModel(key) {
        builder
            .build<F>(
                mainUiApi = mainUiApi,
                key = featureKey
            )
            .viewModelFactory
    }
}

inline fun <reified VM : ViewModel, reified F : Feature> NavGraphBuilder.createScreen(
    appDestination: AppDestination,
    featureBuilder: FeatureBuilder<F>,
    crossinline content: @Composable (VM, List<String>) -> Unit
) {
    composable(
        route = appDestination.navigationRoute,
        arguments = appDestination.composableArguments
    ) { navBackStackEntry ->
        val arguments = appDestination.parseArguments(navBackStackEntry)
        val viewModel: VM = setupComponent(
            builder = featureBuilder,
            key = arguments.hashCode().toString()
        )
        /*TODO maybe good point to make instance of state, event, action here
           and then send in to Content Screen*/
        content(viewModel, arguments)
    }
}