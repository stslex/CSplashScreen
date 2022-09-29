plugins {
    `kotlin-dsl`
}

group = "st.slex.csplashscreen.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "csplashscreen.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }
        register("androidApplication") {
            id = "csplashscreen.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibraryCompose") {
            id = "csplashscreen.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }
        register("androidLibrary") {
            id = "csplashscreen.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidTest") {
            id = "csplashscreen.android.test"
            implementationClass = "AndroidTestPlugin"
        }
        register("androidLibraryJacoco") {
            id = "csplashscreen.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoPlugin"
        }
        register("androidApplicationJacoco") {
            id = "csplashscreen.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoPlugin"
        }
    }
}