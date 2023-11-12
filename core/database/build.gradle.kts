plugins {
    id("csplashscreen.android.library")
}

android.namespace = "st.slex.csplashscreen.core.database"

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(project(":core:core"))
    implementation(libs.bundles.room)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.paging.runtime)
    androidTestApi(libs.androidx.room.testing)
}