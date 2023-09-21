package st.slex.csplashscreen.feature.search.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    dependencies = [SearchPhotosDependencies::class],
    modules = [SearchPhotosModule::class]
)
@SearchPhotosScope
interface SearchPhotosComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: SearchPhotosDependencies): SearchPhotosComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}

