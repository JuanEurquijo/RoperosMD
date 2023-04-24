package org.minutodedios.roperos.ui.screens.home

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import org.minutodedios.roperos.AuthViewModel

@Composable
fun SettingsScreen(
    authViewModel: AuthViewModel = viewModel()
) {
    Surface {
        TextButton(onClick = { authViewModel.authService.logout() }) {
            Text(text = "Logout!")
        }
    }
}