plugins {
    id("csplashscreen.android.library")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core-network"))

    implementation(libs.androidx.paging.runtime)
}

android.namespace = "com.stslex.csplashscreen.core.collection"