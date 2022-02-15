package st.slex.csplashscreen.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import st.slex.csplashscreen.ui.theme.Shapes

@ExperimentalPagerApi
@ExperimentalMaterialApi
fun <T : Any> LazyPagingItems<T>.checkState(scope: LazyListScope) {
    when {
        loadState.append is LoadState.Loading -> scope.loadState()
        loadState.prepend is LoadState.Loading -> scope.loadState()
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
private fun LazyListScope.loadState() = repeat(3) {
    item { ImageItemLoading() }
}

@ExperimentalMaterialApi
@Composable
private fun CoverPhotoItemItemLoading() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .placeholder(
                visible = true,
                shape = RoundedCornerShape(32.dp),
                highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
                color = Color.Gray
            )
    ) {}
}

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun ImageItemLoading(
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
            .fillMaxWidth()
    ) {
        UserImageHeadItemLoading()
        Spacer(modifier = Modifier.padding(4.dp))
        CoverPhotoItemItemLoading()
    }
}


@ExperimentalMaterialApi
@Composable
private fun UserImageHeadItemLoading() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 16.dp, Shapes.medium)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier
                    .size(32.dp)
                    .placeholder(
                        visible = true,
                        shape = CircleShape,
                        highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
                        color = Color.Gray
                    )
            ) {}

            Spacer(modifier = Modifier.padding(8.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
                        color = Color.Gray
                    )
            ) {}

        }
    }
}

