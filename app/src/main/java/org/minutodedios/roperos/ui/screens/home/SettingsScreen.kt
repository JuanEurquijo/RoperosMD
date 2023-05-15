package org.minutodedios.roperos.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import org.minutodedios.roperos.ui.state.AuthViewModel

@Composable
fun SettingsScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    Text(text = "Settings \uD83D\uDE43")
}