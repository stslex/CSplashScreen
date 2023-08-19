package com.stslex.csplashscreen.ui

import androidx.lifecycle.ViewModel
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.navigation.navigator.Navigator

class InitialAppViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun navigate(screen: NavigationScreen) {
        navigator.navigate(screen)
    }
}