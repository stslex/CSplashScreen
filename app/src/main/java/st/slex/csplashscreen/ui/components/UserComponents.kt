package st.slex.csplashscreen.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import st.slex.csplashscreen.ui.navigation.NavHostResource

@ExperimentalMaterial3Api
@Composable
fun UserImageHeadWithUserName(
    modifier: Modifier,
    url: String,
    username: String,
    navController: NavController
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                val destination = NavHostResource.UserScreen.destination
                val route = "$destination/$username"
                navController.navigate(route = route)
            }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageComponent(
                url = url,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.size(16.dp))
            TextUsernamePrimary(username)
        }
    }
}