plugins {
    `kotlin-dsl`
}

group = "st.slex.csplashscreen.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.kotlin.serialization)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "csplashscreen.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "csplashscreen.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "csplashscreen.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "csplashscreen.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}