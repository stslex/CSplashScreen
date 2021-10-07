package st.slex.csplashscreen.ui.screens.search_photos

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import st.slex.csplashscreen.data.search.QuerySearch
import st.slex.csplashscreen.ui.components.MyAppTextFieldColors
import st.slex.csplashscreen.ui.screens.main.LazyPhotosColumn
import javax.inject.Inject


interface SearchPhotosScreen {

    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @Composable
    fun BindScreen(
        args: NavBackStackEntry,
        navController: NavController
    )

    class Base @Inject constructor() : SearchPhotosScreen {

        @Inject
        lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

        @ExperimentalCoilApi
        @ExperimentalMaterialApi
        @ExperimentalPagerApi
        @Composable
        override fun BindScreen(
            args: NavBackStackEntry,
            navController: NavController
        ) {
            val viewModel: SearchViewModel = viewModel(factory = viewModelFactory.get())
            val query = args.arguments?.getString("query").toString()
            viewModel.setQueryPhotosSearch(QuerySearch.SearchPhotos(query))
            val lazyPagingPhotosItems = viewModel.photosSearch.collectAsLazyPagingItems()

            Scaffold(
                topBar = {
                    TopAppBarSearch(querySearch = query) {
                        viewModel.setQueryPhotosSearch(
                            it
                        )
                    }
                }
            ) {
                LazyPhotosColumn(
                    lazyPagingPhotosItems = lazyPagingPhotosItems,
                    navController = navController
                )
            }
        }


        @Composable
        private fun TopAppBarSearch(querySearch: String, search: (QuerySearch) -> Unit) {
            TopAppBar {
                var textInput by remember { mutableStateOf(querySearch) }
                TextField(
                    value = textInput,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onValueChange = { text ->
                        textInput = text
                        search(QuerySearch.SearchPhotos(textInput))
                    },
                    colors = MyAppTextFieldColors(),
                    label = { Text(text = "Input Search") }
                )
            }
        }
    }
}

