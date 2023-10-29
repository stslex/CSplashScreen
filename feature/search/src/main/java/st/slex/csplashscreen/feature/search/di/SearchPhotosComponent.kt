package st.slex.csplashscreen.feature.search.di

import dagger.Component
import st.slex.csplashscreen.core.ui.di.builder.Feature

@Component(
    dependencies = [SearchPhotosDependencies::class],
    modules = [SearchPhotosModule::class]
)
@SearchPhotosScope
interface SearchPhotosComponent : Feature {

    @Component.Factory
    interface Factory {
        fun create(dependencies: SearchPhotosDependencies): SearchPhotosComponent
    }
}
