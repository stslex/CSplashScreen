plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    api(libs.androidx.compose.navigation)

    implementation(libs.androidx.paging.runtime)
}