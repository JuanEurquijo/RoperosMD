package org.minutodedios.roperos.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OperationsScreen(){
    Column {
        Text(text = "Operaciones",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}