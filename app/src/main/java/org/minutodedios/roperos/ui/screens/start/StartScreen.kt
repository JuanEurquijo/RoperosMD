package org.minutodedios.roperos.ui.screens.start

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import org.minutodedios.roperos.R
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@Composable
fun StartScreen(initialScale: Float = 0f, targetValue: Float = 1.8f, onComplete: () -> Unit) {
    val scale = remember { Animatable(initialScale) }

    // Bouncy effect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = targetValue,
            animationSpec = tween(durationMillis = 1000,
                easing = {
                    OvershootInterpolator(5f)
                        .getInterpolation(it)
                }
            ),
        )

        // Startup delay
        delay(2000L)

        // Invoke completed option
        onComplete.invoke()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Image(
            painter = painterResource(id = R.drawable.brand),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .scale(scale.value)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    ApplicationTheme {
        StartScreen(initialScale = 2.0f) {}
    }
}