package org.minutodedios.roperos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.minutodedios.roperos.model.Location

@Composable
fun LocationCard(
    location: Location
) {
    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = location.name,
                style = TextStyle(fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
            )
            Text(text = location.city, textAlign = TextAlign.Center)
        }
    }
}