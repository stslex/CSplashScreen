package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.core.DataResponse
import st.slex.csplashscreen.ui.core.UIResponse

@Module
interface ResponseModule {

    @ExperimentalCoroutinesApi
    @Binds
    fun bindsDataResponse(response: DataResponse.Base): DataResponse

    @ExperimentalCoroutinesApi
    @Binds
    fun bindsUIResponse(response: UIResponse.Base): UIResponse
}