package st.slex.csplashscreen.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import st.slex.csplashscreen.ui.theme.Shapes

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun UserImageHeadWithUserName(
    modifier: Modifier,
    url: String,
    username: String,
    navController: NavController
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 16.dp, Shapes.medium)
            .clip(RoundedCornerShape(16.dp)),
        onClick = {
            //navController.navigate("user_profile")
        }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageUserCircle(url = url, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.padding(8.dp))
            TextUsernamePrimary(username)
        }
    }
}