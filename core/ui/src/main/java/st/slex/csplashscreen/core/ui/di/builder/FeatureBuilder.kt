package st.slex.csplashscreen.core.ui.di.builder

import st.slex.csplashscreen.core.ui.di.MainUiApi

abstract class FeatureBuilder<F : Feature> {

    /** This property is used to get the key of the feature */
    abstract val key: Any

    /**
     * This method is used to create a new instance of the feature
     * @param mainUiApi - the mainUiApi instance
     */
    abstract fun create(mainUiApi: MainUiApi): F

    /**
     * This method is used to get the feature from the FeatureCollector
     * or create a new instance of the feature and add it to the FeatureCollector
     * @param key - the key of the feature
     * @param mainUiApi - the mainUiApi instance
     */
    inline fun <reified T : F> build(
        mainUiApi: MainUiApi,
        key: Any? = null,
    ): T = FeatureCollector.get(keyBuilder(key)) as? T
        ?: setup(mainUiApi, key) as T

    /**
     * This method is used to create a new instance of the feature
     * and add it to the FeatureCollector
     * @param key - the key of the feature
     * @param mainUiApi - the mainUiApi instance
     */
    fun setup(
        mainUiApi: MainUiApi,
        key: Any? = null,
    ): F = create(mainUiApi).also { createdFeature ->
        FeatureCollector.add(keyBuilder(key), createdFeature)
    }

    /**
     * This method is used to remove the feature from the FeatureCollector
     */
    fun clear() {
        FeatureCollector.remove(key)
    }

    /**
     * This method is used to get the key of the feature
     * @param extraKey - the extra key of the feature
     */
    fun keyBuilder(
        extraKey: Any?
    ): Any = extraKey?.let { "$key:$it" } ?: key
}
