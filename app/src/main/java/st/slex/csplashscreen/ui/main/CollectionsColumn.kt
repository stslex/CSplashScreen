package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.animation.AnimationUtils
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
    navController: NavController,
    page: Int = 0,
    scope: PagerScope? = null
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
            .graphicsLayer {
                scope?.let {
                    val pageOffset = it.calculateCurrentOffsetForPage(page)
                    AnimationUtils
                        .lerp(
                            0.85f,
                            1f,
                            1f - pageOffset.coerceIn(0f, 1f)
                        )
                        .also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    alpha = AnimationUtils.lerp(
                        0.5f,
                        1f,
                        1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
            }
            .fillMaxWidth()
            .aspectRatio(1f)) {

        UserImageHeadWithUserName(
            modifier = Modifier,
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
            navController
        )

    }

}

