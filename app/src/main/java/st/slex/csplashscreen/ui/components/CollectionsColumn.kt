package st.slex.csplashscreen.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun CollectionItem(
    item: CollectionModel?,
    modifier: Modifier,
    navController: NavController,
    isUserVisible: Boolean = true
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        if (isUserVisible) {
            UserImageHeadWithUserName(
                modifier = Modifier.fillMaxWidth(),
                url = item?.user?.profile_image?.medium.toString(),
                username = item?.user?.username.toString(),
                navController = navController
            )
        }

        Spacer(modifier = Modifier.padding(4.dp))

        BindCoverImageConstraint(
            item?.id.toString(),
            item?.cover_photo?.urls?.regular.toString(),
            item?.title.toString(),
            item?.total_photos.toString(),
            navController = navController
        )

    }

}

