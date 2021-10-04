package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.ui.theme.Shapes

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun CollectionItem(
    item: CollectionModel?,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {

        UserImageHead(
            url = item?.user?.profile_image?.medium.toString(),
            username = item?.user?.username.toString(),
            navController = navController
        )

        Surface(
            modifier = Modifier.shadow(elevation = 8.dp, Shapes.medium, clip = true),
            onClick = { navController.navigate("collection/${item?.id}") }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clipToBounds(),
                painter = rememberImagePainter(
                    data = item?.cover_photo?.urls?.regular.toString(),
                    builder = {
                        transformations(RoundedCornersTransformation())
                        allowHardware(false)
                        crossfade(500)
                    }
                ),
                contentDescription = "Image",
            )
        }
    }
}