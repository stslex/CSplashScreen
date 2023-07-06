package com.stslex.csplashscreen.ui.components.bottom_appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
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
fun MainBottomAppBar(
    onBottomAppBarClick: (NavigationScreen) -> Unit,
    startDestination: AppDestination
) {
    val selectedItem = remember { mutableStateOf(startDestination) }
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        BottomAppBarResource.values().forEach { item ->
            val isSelected = selectedItem.value == item.appDestination
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    selectedItem.value = item.appDestination
                    onBottomAppBarClick(item.screen)
                },
                icon = {
                    val icon = if (isSelected) {
                        item.selectedIcon
                    } else {
                        item.unselectedIcon
                    }
                    Icon(icon, item.appDestination.route)
                },
                label = { Text(text = stringResource(id = item.titleResource)) },
                alwaysShowLabel = false
            )
        }
    }
}