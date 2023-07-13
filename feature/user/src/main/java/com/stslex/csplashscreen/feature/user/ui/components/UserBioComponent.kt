package com.stslex.csplashscreen.feature.user.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private enum class UserBioState(val value: Int) {
    COLLAPSE(1),
    EXPAND(Int.MAX_VALUE);

    fun click() = when (this) {
        COLLAPSE -> EXPAND
        EXPAND -> COLLAPSE
    }
}

@Composable
fun BindUserBio(
    modifier: Modifier = Modifier, bioText: String
) {
    val userBioState = remember {
        mutableStateOf(UserBioState.COLLAPSE)
    }
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        AnimatedContent(
            targetState = userBioState.value,
            label = "userBioState"
        ) { target ->
            Text(
                modifier = Modifier
                    .clickable {
                        userBioState.value = userBioState.value.click()
                    }
                    .padding(16.dp),
                text = bioText,
                maxLines = target.value,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}