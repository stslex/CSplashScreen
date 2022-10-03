package st.slex.csplashscreen.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

private val listOfItems: List<BottomAppBarResource> = listOf(
    BottomAppBarResource.TopicsScreen,
    BottomAppBarResource.MainScreen,
    BottomAppBarResource.SearchScreen
)

@Composable
fun mainBottomAppBar(navController: NavController): @Composable () -> Unit = {
    val selectedItem = remember {
        mutableStateOf(BottomAppBarResource.MainScreen.destination)
    }
    val isSelected = remember {
        mutableStateOf(false)
    }
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        listOfItems.forEach { item ->
            isSelected.value = selectedItem.value == item.destination
            NavigationBarItem(
                selected = isSelected.value,
                onClick = onBottomAppBarClick(navController, item, selectedItem),
                icon = {
                    val icon = if (isSelected.value) item.selectedIcon else item.unselectedIcon
                    Icon(icon, item.destination)
                },
                label = { Text(text = item.title) },
                alwaysShowLabel = false
            )
        }
    }
}

private fun onBottomAppBarClick(
    navController: NavController,
    item: BottomAppBarResource,
    selectedItem: MutableState<String>
): () -> Unit = {
    selectedItem.value = item.destination
    navController.navigate(item.route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                inclusive = true
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = false
    }
}