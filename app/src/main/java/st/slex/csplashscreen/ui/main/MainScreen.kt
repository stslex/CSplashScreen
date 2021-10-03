package st.slex.csplashscreen.ui.main

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Button(onClick = {
        navController.navigate("detail")
    }) {
        Text(text = "go to detail")
    }
}


@Composable
private fun BindImage() {

}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(navController = NavController(LocalContext.current))
}