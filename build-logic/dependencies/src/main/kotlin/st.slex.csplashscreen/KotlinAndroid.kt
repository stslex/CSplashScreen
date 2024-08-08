package st.slex.csplashscreen

import AppExt.androidTestImplementationBundle
import AppExt.coreLibraryDesugaring
import AppExt.findVersionInt
import AppExt.implementation
import AppExt.implementationBundle
import AppExt.ksp
import AppExt.libs
import AppExt.testImplementationBundle
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DefaultConfig
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.properties.Properties

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    val libs = libs

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
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            isCoreLibraryDesugaringEnabled = true
        }
    }

    configureKotlin()

    extensions.configure<KspExtension> {
        arg("KOIN_CONFIG_CHECK", "true")
    }

    dependencies {
        coreLibraryDesugaring("android-desugarJdkLibs")
        implementation("androidx-core-ktx", "kotlinx-collections-immutable", "coroutines")
        androidTestImplementationBundle("android-test")
        implementationBundle("koin")
        testImplementationBundle("test")
        ksp("koin-ksp")
    }
}

/**
 * Configure base Kotlin options
 */
private fun Project.configureKotlin() {
    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            // Set JVM target to 11
            jvmTarget.set(JvmTarget.JVM_17)
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors.set(warningsAsErrors?.toBoolean() ?: false)
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
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
