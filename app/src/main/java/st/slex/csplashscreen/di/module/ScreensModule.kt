package st.slex.csplashscreen.di.module

import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.screens.collection.SingleCollectionScreen
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.user.UserScreen

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Module
interface ScreensModule {

    @Binds
    fun bindsDetailScreen(screen: ImageDetailScreen.Base): ImageDetailScreen

    @Binds
    fun bindsMainScreen(screen: MainScreen.Base): MainScreen

    @Binds
    fun bindsSingleCollectionScreen(screen: SingleCollectionScreen.Base): SingleCollectionScreen

    @Binds
    fun bindsRawImageScreen(screen: RawImageScreen.Base): RawImageScreen

    @Binds
    fun bindsSearchPhotosScreen(screen: SearchPhotosScreen.Base): SearchPhotosScreen

    @Binds
    fun bindsUserScreen(screen: UserScreen.Base): UserScreen
}