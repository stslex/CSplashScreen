plugins {
    id("csplashscreen.android.library")
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:photos"))

    implementation(libs.bundles.room)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.paging.runtime)
    implementation("com.google.code.gson:gson:2.10.1")
}

android.namespace = "st.slex.csplashscreen.core.favourite"