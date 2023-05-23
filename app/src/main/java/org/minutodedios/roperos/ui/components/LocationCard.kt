package org.minutodedios.roperos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.minutodedios.roperos.model.Location
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@Composable
fun LocationCard(
    modifier: Modifier = Modifier,
    location: Location
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(18.dp),
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

@Composable
@Preview
fun LocationCardPreview() {
    ApplicationTheme {
        LocationCard(location = Location("lorem_ipsum", "Lorem", "Ipsum"))
    }
}