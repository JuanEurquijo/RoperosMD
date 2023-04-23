package org.minutodedios.roperos.ui.screens.home

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import org.minutodedios.roperos.ApplicationViewModel

@Composable
fun SettingsScreen(
    applicationViewModel: ApplicationViewModel = viewModel()
) {
    Surface {
        TextButton(onClick = { applicationViewModel.authService.logout() }) {
            Text(text = "Logout!")
        }
    }
}