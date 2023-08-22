import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

plugins {
    id("csplashscreen.android.application")
    id("csplashscreen.android.application.compose")
}

android {
    signingConfigs {
        val keystoreProperties = gradleKeystoreProperties(project.rootProject.projectDir)
        create("release") {
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storeFile = getFile(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
        }
        with(get("debug")) {
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storeFile = getFile(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
    }
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:collection"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:photos"))
    implementation(project(":core:favourite"))

    implementation(project(":feature:home"))
    implementation(project(":feature:collection"))
    implementation(project(":feature:user"))
    implementation(project(":feature:photo-detail"))
    implementation(project(":feature:search"))
    implementation(project(":feature:favourite"))
}

fun getFile(path: String): File {
    val file = File(project.rootProject.projectDir, path)
    if (file.isFile) {
        return file
    } else {
        throw IllegalStateException("${file.name} is inValid")
    }
}

fun gradleKeystoreProperties(projectRootDir: File): Properties {
    val properties = Properties()
    val localProperties = File(projectRootDir, "keystore.properties")

    if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    }
    return properties
}