package org.minutodedios.roperos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SummaryScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Aporte realizado correctamente",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
            ), fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(60.dp))

        ElevatedButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {  },
        ) {
            Text(text = "Guardar Venta")
        }


    }
}