package st.slex.feature_image_raw.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import st.slex.feature_image_raw.ui.RawImageViewModel

val moduleRawImage = module {
    viewModelOf(::RawImageViewModel)
}