plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
    kotlin("kapt")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core-network"))
    implementation(project(":core:photos"))

    val room_version = "2.5.2"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-paging:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    implementation(libs.androidx.paging.runtime)
}

android.namespace = "com.stslex.csplashscreen.feature.search"