package st.slex.csplashscreen.ui.components.bottom_appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import st.slex.csplashscreen.core.navigation.Screen

@Composable
fun MainBottomAppBar(
    onBottomAppBarClick: (Screen) -> Unit,
    currentDestination: Screen?
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        BottomAppBarResource
            .entries
            .forEach { item ->
                val isSelected = currentDestination == item.appDestination
                BottomAppBarItem(
                    item = item,
                    isSelected = isSelected,
                    onBottomAppBarClick = remember(item) {
                        onBottomAppBarClick
                    }
                )
            }
    }
}

@Composable
private fun RowScope.BottomAppBarItem(
    item: BottomAppBarResource,
    isSelected: Boolean,
    onBottomAppBarClick: (Screen) -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = {
            onBottomAppBarClick(item.screen)
        },
        icon = {
            Icon(
                imageVector = item.getIcon(isSelected),
                contentDescription = item.screen.javaClass.simpleName
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
