package st.slex.csplashscreen.ui.screens.main

import android.os.Parcelable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.androidx.AndroidScreen
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import st.slex.csplashscreen.appComponent
import javax.inject.Inject


@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@FlowPreview
class MainScreen : AndroidScreen() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    @ExperimentalAnimationApi
    @Composable
    override fun Content() {
        LocalContext.current.applicationContext.appComponent.inject(this)
        SideEffect(sideEffect())
        SetUpPager()
    }

    @Composable
    private fun sideEffect(
        systemUiController: SystemUiController = rememberSystemUiController(),
        useDarkIcons: Boolean = MaterialTheme.colors.isLight
    ): () -> Unit = {
        systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = useDarkIcons)
    }

    @ExperimentalAnimationApi
    @Composable
    private fun SetUpPager(
        viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory.get()),
        pagerState: PagerState = rememberPagerState()
    ) {
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                sendPageSelectedEvent(page)
            }
        }
        val listOfPagingItems = with(viewModel) {
            listOf(
                MainPagerTabResource.Photos(photos.collectAsLazyPagingItems()),
                MainPagerTabResource.Collections(collections.collectAsLazyPagingItems())
            )
        }
        LazyListColumn(pagerState, listOfPagingItems)
    }

    @ExperimentalAnimationApi
    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @Composable
    private fun LazyListColumn(
        pagerState: PagerState = rememberPagerState(),
        pagesResource: List<MainPagerTabResource<out Parcelable>>,
    ) {
        Column {
            TabRow(pagerState = pagerState, listPagesResource = pagesResource)
            Pager(
                pagesResource = pagesResource,
                pagerState = pagerState
            )
        }
    }

    @Suppress("UNUSED_PARAMETER")
    companion object AnalyticsService {
        fun sendPageSelectedEvent(page: Int) = Unit
    }
}






