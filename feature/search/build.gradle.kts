plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:photos"))
    implementation(project(":core:database"))

    implementation(libs.androidx.paging.runtime)
}
