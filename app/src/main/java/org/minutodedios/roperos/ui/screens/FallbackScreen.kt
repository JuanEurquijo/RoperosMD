package org.minutodedios.roperos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineStops
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@Composable
fun FallbackScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Filled.AirlineStops,
            contentDescription = "AirlineStops",
            modifier = Modifier.size(64.dp)
        )
        Text(
            text = "Oops...",
            style = TextStyle(
                fontSize = 32.sp
            )
        )
        Text(
            text = "Parece ser que esta función aún no ha sido implementada",
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FallbackScreenPreview() {
    ApplicationTheme {
        FallbackScreen()
    }
}