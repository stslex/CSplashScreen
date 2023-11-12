package st.slex.csplashscreen.core.ui.di.builder

import st.slex.csplashscreen.core.ui.di.MainUiApi

interface FeatureBuilder<F : Feature> {

    var feature: F?

    fun create(mainUiApi: MainUiApi): F

    fun build(
        mainUiApi: MainUiApi
    ): F = feature ?: create(mainUiApi)
        .also { createdFeature ->
            feature = createdFeature
        }

    fun clear() {
        feature = null
    }
}
