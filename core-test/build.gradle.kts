plugins {
    id("csplashscreen.android.library")
}

dependencies {
    libs.apply {
        testApi(junit)
        testApi(androidx.test.ext.junit)
        androidTestApi(androidx.test.espresso.core)
    }
}

android {
    namespace = "st.slex.core_test"
}