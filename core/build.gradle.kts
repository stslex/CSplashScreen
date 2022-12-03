plugins {
    id("csplashscreen.android.library")
}

dependencies {
    implementation(project(":core-test"))
    libs.apply {
        api(androidx.core.ktx)
        api(kotlin.reflect)
        api(bundles.lifecycle)
        api(bundles.coroutines)
        api(koin.android)
    }
}

android {
    namespace = "st.slex.core"
}