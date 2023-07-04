package com.stslex.csplashscreen.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.core.navigation.NavigationScreen

@Composable
fun mainBottomAppBar(
    onBottomAppBarClick: (NavigationScreen) -> Unit,
    startDestination: String = AppDestination.HOME.route
): @Composable () -> Unit = {
    val selectedItem = remember { mutableStateOf(startDestination) }
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomAppBarResource.values().forEach { item ->
            val isSelected = selectedItem.value == item.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    selectedItem.value = item.route
                    onBottomAppBarClick(item.screen)
                },
                icon = {
                    val icon = if (isSelected) {
                        item.selectedIcon
                    } else {
                        item.unselectedIcon
                    }
                    Icon(icon, item.route)
                },
                label = { Text(text = stringResource(id = item.titleResource)) },
                alwaysShowLabel = false
            )
        }
    }
}