package st.slex.csplashscreen.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import st.slex.csplashscreen.ui.navigation.NavActions
import st.slex.csplashscreen.ui.navigation.Navigator

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun UserImageHeadWithUserName(
    modifier: Modifier,
    url: String,
    username: String,
    navigator: Navigator
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 0.dp,
        onClick = {
            navigator.navigate(NavActions.UserScreen(username))
        }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageUserCircle(url = url, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.size(16.dp))
            TextUsernamePrimary(username)
        }
    }
}