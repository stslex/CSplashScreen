package st.slex.csplashscreen.core.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.DpSize

@Composable
fun DimensionSubcomposeLayout(
    modifier: Modifier = Modifier,
    mainContent: @Composable () -> Unit,
    dependentContent: @Composable (DpSize) -> Unit
) {

    SubcomposeLayout(
        modifier = modifier
    ) { constraints: Constraints ->

        val mainPlaceable: Placeable? = subcompose(SubcomposeType.MAIN, mainContent)
            .map { measurable: Measurable ->
                measurable.measure(
                    constraints = constraints.copy(
                        minWidth = 0,
                        minHeight = 0
                    )
                )
            }
            .firstOrNull()


        val dependentPlaceable: Placeable? = subcompose(SubcomposeType.DEPEND) {
            dependentContent(
                mainPlaceable
                    ?.let { placeable: Placeable ->
                        DpSize(
                            density.run { (placeable.width).toDp() },
                            density.run { placeable.height.toDp() }
                        )
                    }
                    ?: DpSize.Zero

            )
        }
            .map { measurable: Measurable ->
                measurable.measure(constraints = constraints)
            }
            .firstOrNull()


        layout(mainPlaceable?.width ?: 0, mainPlaceable?.height ?: 0) {
            dependentPlaceable?.placeRelative(0, 0)
        }
    }
}

