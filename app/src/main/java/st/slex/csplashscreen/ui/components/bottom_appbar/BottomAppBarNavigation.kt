package st.slex.csplashscreen.ui.components.bottom_appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavigationScreen

@Composable
fun MainBottomAppBar(
    onBottomAppBarClick: (NavigationScreen) -> Unit,
    currentDestination: AppDestination?
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        BottomAppBarResource.values().forEach { item ->
            val isSelected = currentDestination == item.appDestination
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onBottomAppBarClick(item.screen)
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = item.appDestination.name
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.titleResource)
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}
