plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.room.library)
}

dependencies {
    implementation(project(":core:core"))
}