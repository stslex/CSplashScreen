package com.stslex.csplashscreen.feature.search.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

@Composable
fun TopAppBarSearch(
    querySearch: String,
    search: (String) -> Unit
) {
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(Unit) {
        if (querySearch.isBlank()) {
            focusRequester.requestFocus()
        }
    }
    TextField(
        modifier = Modifier
            .focusRequester(focusRequester)
            .fillMaxWidth(),
        value = querySearch,
        onValueChange = { text ->
            if (querySearch != text) {
                search(text)
            }
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        trailingIcon = {
            IconButton(
                onClick = {
                    search("")
                    focusRequester.freeFocus()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null
                )
            }
        },
        keyboardOptions = KeyboardOptions()
    )
}
