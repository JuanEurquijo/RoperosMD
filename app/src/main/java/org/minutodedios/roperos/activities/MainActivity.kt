package org.minutodedios.roperos.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import org.minutodedios.roperos.AuthViewModel
import org.minutodedios.roperos.ui.screens.login.LoginScreen
import org.minutodedios.roperos.ui.screens.main.ClosetSelectionScreen
import org.minutodedios.roperos.ui.theme.ApplicationTheme

/**
 * Actividad principal que llamará a la vista principal de JetpackCompose
 * Se encarga de proveer el ViewModel de autenticación a los composables
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            ApplicationTheme {
                AuthenticatedComposable()
            }
        }
    }

    @Composable
    fun AuthenticatedComposable() {
        if (authViewModel.authenticated) {
            ClosetSelectionScreen()
        } else {
            LoginScreen()
        }
    }

}