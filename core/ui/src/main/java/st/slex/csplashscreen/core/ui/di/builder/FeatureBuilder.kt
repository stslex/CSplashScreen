package st.slex.csplashscreen.core.ui.di.builder

import android.content.Context

interface FeatureBuilder<F : Feature> {

    var feature: F?

    fun create(context: Context): F

    fun build(
        context: Context
    ): F = feature ?: create(context)
        .also { createdFeature ->
            feature = createdFeature
        }

    fun clear() {
        feature = null
    }
}
