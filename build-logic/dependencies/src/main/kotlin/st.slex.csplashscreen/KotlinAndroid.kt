package st.slex.csplashscreen

import AppExt.currentLibs
import AppExt.findVersionInt
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DefaultConfig
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.properties.Properties

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    val libs = currentLibs

    commonExtension.apply {

        compileSdk = libs.findVersionInt("compileSdk")

        defaultConfig {
            minSdk = libs.findVersionInt("minSdk")
            buildFeatures.buildConfig = true

            gradleLocalProperties(
                projectRootDir = project.rootProject.projectDir,
                providers = providers
            ).let { properties ->
                setLocalProperties(properties)
            }
        }

        compileOptions {
            // Up to Java 11 APIs are available through desugaring
            // https://developer.android.com/studio/write/java11-minimal-support-table
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
            isCoreLibraryDesugaringEnabled = true
        }
    }

    configureKotlin()

    dependencies {
        "coreLibraryDesugaring"(libs.findLibrary("android-desugarJdkLibs").get())
        "implementation"(libs.findLibrary("androidx-core-ktx").get())
        "testImplementation"(libs.findBundle("test").get())
        "androidTestImplementation"(libs.findBundle("android-test").get())
        "implementation"(libs.findLibrary("kotlinx-collections-immutable").get())
        "implementation"(libs.findLibrary("coroutines").get())
        "implementation"(libs.findBundle("koin").get())
        "ksp"(libs.findLibrary("koin-ksp").get())
    }
}

private fun CommonExtension<*, *, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

// TODO check
///**
// * Configure base Kotlin options for JVM (non-Android)
// */
//internal fun Project.configureKotlinJvm() {
//    extensions.configure<JavaPluginExtension> {
//        // Up to Java 11 APIs are available through desugaring
//        // https://developer.android.com/studio/write/java11-minimal-support-table
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//
//    configureKotlin()
//}

/**
 * Configure base Kotlin options
 */
private fun Project.configureKotlin() {
    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            // Set JVM target to 11
            jvmTarget = JavaVersion.VERSION_11.toString()
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
            )
        }
    }
}

fun DefaultConfig.setLocalProperties(
    properties: Properties
) {
    LocalProperties.values().forEach { property ->
        properties[property.key]
            ?.toString()
            ?.let { value ->
                buildConfigField(property.type, property.buildName, value)
            }
            ?: throw IllegalStateException("API_KEY should be initialised")
    }
}
