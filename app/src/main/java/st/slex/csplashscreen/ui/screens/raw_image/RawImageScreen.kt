package st.slex.csplashscreen.ui.screens.raw_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import javax.inject.Inject


interface RawImageScreen {

    @ExperimentalCoilApi
    @Composable
    fun BindScreen(
        args: NavBackStackEntry,
        navController: NavController
    )

    class Base @Inject constructor() : RawImageScreen {

        @ExperimentalCoilApi
        @Composable
        override fun BindScreen(args: NavBackStackEntry, navController: NavController) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .clickable { navController.popBackStack() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = args.arguments?.getString("url").toString(),
                        builder = {
                            allowHardware(false)
                            crossfade(500)
                        }
                    ),
                    contentDescription = "TestImage"
                )
            }
        }

    }

}