package st.slex.feature_photo_detail.ui.components

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.Language
import st.slex.core_ui.theme.AppTheme
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun DynamicSwipeDismiss(
    modifier: Modifier = Modifier
) {
    SwipeDismiss(modifier)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(device = "id:pixel_6_pro", showSystemUi = true)
@Composable
fun DynamicSwipeDismissPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DynamicSwipeDismiss(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SwipeDismiss(
    modifier: Modifier = Modifier
) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val state = rememberDismissState(
            confirmStateChange = {
                false
            }
        )

        SwipeToDismiss(
            state = state,
            modifier = Modifier
                .padding(16.dp)
                .shadow(2.dp, RoundedCornerShape(20.dp))
                .background(Color(0xFFFDFDFD)),
            background = {
                MetaContainer(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFE01111),
                    cutoff = .3f
                ) {
                    val willTrigger by remember {
                        derivedStateOf {
                            state.progress.fraction > .6f
                        }
                    }
                    MetaEntity(
                        modifier = Modifier
                            .align(Alignment.CenterEnd),
                        blur = 50f,
                        metaContent = {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                val width by animateDpAsState(
                                    targetValue = if (!willTrigger) 20.dp else 13.dp,
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioNoBouncy,
                                        stiffness = Spring.StiffnessLow,
                                    )
                                )
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .background(Color.Black)
                                        .requiredHeight(120.dp)
                                        .width(width)
                                )

                                androidx.compose.animation.AnimatedVisibility(
                                    visible = !willTrigger,
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd),
                                    enter = fadeIn(),
                                    exit = fadeOut(),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .background(Color.Black)
                                            .width(50.dp)
                                            .height(12.dp)
                                    )
                                }
                            }
                        }
                    )


                    val bounce = remember {
                        Animatable(0f)
                    }

                    LaunchedEffect(willTrigger) {
                        if (willTrigger) {
                            bounce.animateTo(
                                -10f,
                                spring(
                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                    stiffness = Spring.StiffnessHigh,
                                )
                            )
                            bounce.animateTo(
                                0f,
                                spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessMedium
                                )
                            )
                        }
                    }

                    MetaEntity(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .offset {
                                IntOffset(bounce.value.roundToInt(), 0)
                            }
                            .offset {
                                IntOffset(state.offset.value.roundToInt() / 4, 0)
                            },
                        blur = 50f,
                        metaContent = {
                            Box(
                                modifier = Modifier
                                    .background(Color.Black, CircleShape)
                                    .size(50.dp)
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color(0xFFFFFFFF)
                        )
                    }
                }
            }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                backgroundColor = Color(0xFFAEE0FA),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Swipe to delete",
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraLight),
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MetaContainer(
    modifier: Modifier = Modifier,
    cutoff: Float = .5f,
    color: Color = Color.Black,
    content: @Composable BoxScope.() -> Unit,
) {
    val metaShader = remember {
        RuntimeShader(ShaderSource)
    }
    Box(
        modifier = modifier
            .graphicsLayer {
                metaShader.setFloatUniform("cutoff", cutoff)
                metaShader.setFloatUniform("rgb", color.red, color.green, color.blue)
                renderEffect = RenderEffect
                    .createRuntimeShaderEffect(
                        metaShader, "composable"
                    )
                    .asComposeRenderEffect()
            },
        content = content
    )
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MetaEntity(
    modifier: Modifier = Modifier,
    blur: Float = 30f,
    metaContent: @Composable BoxScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit = {},
) {

    Box(
        modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .customBlur(blur),
            content = metaContent,
        )
        content()
    }

}

@RequiresApi(Build.VERSION_CODES.S)
fun Modifier.customBlur(blur: Float) = this.then(
    graphicsLayer {
        if (blur > 0f)
            renderEffect = RenderEffect
                .createBlurEffect(
                    blur,
                    blur,
                    Shader.TileMode.DECAL,
                )
                .asComposeRenderEffect()
    }
)

@Language("AGSL")
const val ShaderSource = """
    uniform shader composable;
    
    uniform float cutoff;
    uniform float3 rgb;
    
    half4 main(float2 fragCoord) {
        half4 color = composable.eval(fragCoord);
        color.a = step(cutoff, color.a);
        if (color == half4(0.0, 0.0, 0.0, 1.0)) {
            color.rgb = half3(rgb[0], rgb[1], rgb[2]);
        }
        return color;
    }
"""

//@Language("AGSL")
//const val ShaderSource = """
//    uniform shader composable;
//
//    uniform float2 size;
//    uniform float cutoff;
//
//    half4 main(float2 fragCoord) {
//
//        half4 color = composable.eval(fragCoord);
//
//        float alpha = color.a;
//        float red = color.r;
//        float green = color.g;
//        float blue = color.b;
//        if (alpha > cutoff) {
//            alpha = 1.0;
//        } else {
//            alpha = 0.1;
////            red = 0.0;
////            green = 0.0;
////            blue = 0.0;
//        }
//
//        color = half4(red, green, blue, alpha);
////        color = half4(alpha, 0.0, 0.0, alpha);
//        return color;
//    }
//"""
