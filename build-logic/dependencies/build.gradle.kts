plugins {
    `kotlin-dsl`
}

group = "st.slex.csplashscreen.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.kotlin.serialization)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.vkompose.gradlePlugin)
    compileOnly(libs.composeCompiler.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
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
        register("roomLibrary") {
            id = "csplashscreen.room.library"
            implementationClass = "RoomLibraryConventionPlugin"
        }
    }
}