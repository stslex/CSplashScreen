package st.slex.csplashscreen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import st.slex.csplashscreen.ui.main.CoverPhotoItem
import st.slex.csplashscreen.ui.theme.TransparentGray
import st.slex.csplashscreen.ui.theme.Typography

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun BindCoverImageConstraint(
    id: String,
    url: String,
    title: String,
    totalPhotos: String,
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(color = MaterialTheme.colors.surface)
    ) {
        val (background, content) = createRefs()
        CoverPhotoItem(url)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(32.dp))
                .constrainAs(background) {
                    centerTo(parent)
                },
            color = TransparentGray,
            onClick = { navController.navigate("collection/$id") },
        ) {}
        CollectionTitleAndNumComponent(
            modifier = Modifier.constrainAs(content) {
                bottom.linkTo(parent.bottom)
            },
            title = title,
            totalPhotos = totalPhotos
        )
    }
}

@Composable
fun CollectionTitleAndNumComponent(modifier: Modifier, title: String, totalPhotos: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 16.dp)
    ) {
        CollectionTextCard(text = title, Typography.h4)
        Spacer(modifier = Modifier.padding(4.dp))
        CollectionTextCard(text = "$totalPhotos Photos", style = Typography.h5)
    }
}