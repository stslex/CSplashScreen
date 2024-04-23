package st.slex.csplashscreen.core.ui.di.builder

object FeatureCollector {

    private val features: MutableMap<Any, Feature> = mutableMapOf()

    fun get(key: Any): Feature? = features[key]

    internal fun add(key: Any, feature: Feature) {
        features[key] = feature
    }

    internal fun remove(key: Any) {
        features.remove(key)
    }
}