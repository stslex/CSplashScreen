plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-network"))

    libs.apply {
        api(android.material)
        api(androidx.appcompat)
        api(androidx.activity.compose)
        api(androidx.compose.ui.core)
        api(androidx.compose.material3)
        api(androidx.compose.ui.tooling.preview)

        api(androidx.constraintlayout.compose)

        api(accompanist.pager.core)
        api(accompanist.pager.indicators)
        api(accompanist.placeholder)
        api(accompanist.appcompat.theme)
        api(accompanist.systemuicontroller)
        api(accompanist.flowlayout)
        api(accompanist.navigation.animation)
        api(accompanist.insets)
        api(accompanist.insets.ui)

        api(androidx.paging.compose)

        api(landscapist.glide)
        api(koin.compose)

        androidTestApi(androidx.compose.ui.test.junit4)
        androidTestApi(androidx.compose.ui.tooling)
    }
}

android {
    namespace = "st.slex.core_ui"
}