import AppExt.findVersionInt
import AppExt.findVersionString
import AppExt.libs
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import st.slex.csplashscreen.configureKotlinAndroid
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                namespace = "st.slex.csplashscreen"
                defaultConfig.apply {
                    applicationId = "st.slex.csplashscreen"
                    targetSdk = libs.findVersionInt("targetSdk")
                    versionName = libs.findVersionString("versionName")
                    versionCode = libs.findVersionInt("versionCode")

                    configureSigning(target)
                }
            }
        }
    }
}

fun ApplicationExtension.configureSigning(
    project: Project
) {
    signingConfigs {
        val keystoreProperties = gradleKeystoreProperties(project.rootProject.projectDir)
        create("release") {
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storeFile = project.getFile(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
        }
        with(getByName("debug")) {
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storeFile = project.getFile(keystoreProperties.getProperty("storeFile"))
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


fun Project.getFile(path: String): File {
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