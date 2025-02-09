plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:database"))
    implementation(project(":core:photos"))

    implementation(libs.androidx.paging.runtime)
    implementation(libs.kotlinx.serialization.json)
}
