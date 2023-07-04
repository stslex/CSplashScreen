plugins {
    id("csplashscreen.android.library")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-network"))

    implementation(libs.androidx.paging.runtime)
}

android.namespace = "st.slex.core_collection"