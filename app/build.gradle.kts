plugins {
    id("csplashscreen.android.application")
    id("csplashscreen.android.application.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core-navigation"))
    implementation(project(":core-network"))
    implementation(project(":core-photos"))
    implementation(project(":core-collection"))
    implementation(project(":feature-home"))
    implementation(project(":feature-collection"))
    implementation(project(":feature-user"))
    implementation(project(":feature-photo-detail"))
    implementation(project(":feature-image-raw"))
    implementation(project(":feature-topics"))
    implementation(project(":feature-search-photos"))

    implementation(libs.androidx.paging.runtime)
}