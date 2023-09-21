plugins {
    id("csplashscreen.android.library")
}

android.namespace = "st.slex.csplashscreen.core.database"

dependencies {
    implementation(project(":core:core"))
    implementation(libs.bundles.room)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.paging.runtime)
    androidTestApi(libs.androidx.room.testing)
}