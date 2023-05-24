package org.minutodedios.roperos.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MinutoDeDiosColorScheme = lightColorScheme(
    primary = Color(0xfffec907),
    secondary = Color(0xff00407d),
    tertiary = Color.Blue
)

@Composable
fun ApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MinutoDeDiosColorScheme,
        typography = Typography,
        content = content
    )
}