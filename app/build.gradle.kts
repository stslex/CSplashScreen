plugins {
    id("csplashscreen.android.application")
    id("csplashscreen.android.application.compose")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

@Suppress("UnstableApiUsage")
android {
    defaultConfig {
        applicationId = "st.slex.csplashscreen"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    namespace = "st.slex.csplashscreen"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-ui"))
    implementation(project(":core-navigation"))
    implementation(project(":core-network"))
    implementation(project(":feature-home"))

    libs.apply {
        implementation(hilt.android.core)
        kapt(libs.hilt.android.compiler)
        implementation(androidx.paging.runtime)
    }
}