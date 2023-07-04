package st.slex.feature_image_raw.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import com.stslex.csplashscreen.core.core.AppModule
import st.slex.feature_image_raw.ui.RawImageViewModel

class ModuleRawImage : AppModule {

    override fun invoke(): Module = module {
        viewModelOf(::RawImageViewModel)
    }
}