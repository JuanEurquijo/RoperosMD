package org.minutodedios.roperos.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.minutodedios.roperos.navigation.RootNavigationGraph
import org.minutodedios.roperos.ui.screens.LoadingScreen
import org.minutodedios.roperos.ui.screens.LoginScreen
import org.minutodedios.roperos.ui.state.AuthViewModel
import org.minutodedios.roperos.ui.theme.ApplicationTheme

/**
 * Actividad principal que llamará a la vista principal de JetpackCompose
 * Se encarga de proveer el ViewModel de autenticación a los composables
 */
@AndroidEntryPoint
class RootActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            val isAuthenticated = authViewModel.authenticated.observeAsState()
            val isAuthenticationReady = authViewModel.isReady.observeAsState()

            ApplicationTheme {
                if (isAuthenticated.value!!)
                    RootNavigationGraph(navController = rememberNavController())
                else if (isAuthenticationReady.value!!)
                    LoginScreen()
                else
                    LoadingScreen()
            }
        }
    }
}