package st.slex.csplashscreen.ui.screens.main

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
import st.slex.csplashscreen.ui.components.BindCoverImageConstraint
import st.slex.csplashscreen.ui.components.UserImageHeadWithUserName

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun CollectionItem(
    item: CollectionModel?,
    modifier: Modifier,
    navController: NavController
) {

    Column(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
            .fillMaxWidth()
    ) {

        UserImageHeadWithUserName(
            modifier = Modifier.fillMaxWidth(),
            url = item?.user?.profile_image?.medium.toString(),
            username = item?.user?.username.toString(),
            navController = navController
        )

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

