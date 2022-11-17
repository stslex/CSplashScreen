plugins {
    id("csplashscreen.android.library")
}

dependencies {
    libs.apply {
        api(junit)
        api(androidx.test.ext.junit)
        api(androidx.test.espresso.core)
    }
}

android {
    namespace = "st.slex.core_test"
}