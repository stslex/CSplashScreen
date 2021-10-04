package st.slex.csplashscreen.ui.collection

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.main.MainViewModel

@ExperimentalCoroutinesApi
@Composable
fun Collection(navController: NavController, viewModel: MainViewModel, collectionId: String) {
    Text(text = collectionId)
}