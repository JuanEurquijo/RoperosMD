package org.minutodedios.roperos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Espera mientras realizamos algunas comprobaciones",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        LinearProgressIndicator()
    }
}

@Composable
@Preview(showBackground = true)
fun LoginReadyScreenPreview() {
    ApplicationTheme {
        LoadingScreen()
    }
}