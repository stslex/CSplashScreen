package st.slex.csplashscreen.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import st.slex.csplashscreen.ui.screens.user.UserScreen

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun UserImageHeadWithUserName(
    modifier: Modifier,
    url: String,
    username: String,
    navigator: Navigator = LocalNavigator.currentOrThrow
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 0.dp,
        onClick = {
            navigator.push(UserScreen(username))
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