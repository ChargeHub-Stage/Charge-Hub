package com.wisemen.chargehub.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Colors.sanJuan,
    background = Colors.white,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colors = LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}