package st.slex.csplashscreen.core.ui.di.builder

import android.content.Context

interface FeatureBuilder {

    fun create(context: Context): Feature

    fun build(context: Context): Feature = _feature ?: create(context)
        .also { createdFeature ->
            _feature = createdFeature
        }

    companion object {
        private var _feature: Feature? = null
    }
}