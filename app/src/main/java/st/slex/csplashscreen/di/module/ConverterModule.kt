package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.data.core.DataResponseConverter
import st.slex.csplashscreen.ui.screens.detail.DownloadImageResource

@Module
interface ConverterModule {

    @Binds
    fun bindsDataResponseConverter(converter: DataResponseConverter.Base): DataResponseConverter

    @Binds
    fun bindsDownloadingImageResource(converter: DownloadImageResource.Base): DownloadImageResource
}