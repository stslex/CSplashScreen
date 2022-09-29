plugins {
    id("csplashscreen.android.library")
}

dependencies {
    implementation(project(":core-test"))
    libs.apply {
        api(androidx.core.ktx)
        api(kotlin.reflect)
        api(androidx.lifecycle.runtime.ktx)
        api(androidx.lifecycle.viewmodel.ktx)
        api(androidx.lifecycle.viewmodel.compose)
        api(kotlinx.coroutines.core)
        api(kotlinx.coroutines.android)
    }
}

android {
    namespace = "st.slex.core"
}