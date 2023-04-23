package org.minutodedios.roperos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.ApplicationViewModel
import org.minutodedios.roperos.ui.screens.start.StartScreen
import org.minutodedios.roperos.ui.screens.home.HomeScreen
import org.minutodedios.roperos.ui.screens.home.SettingsScreen
import org.minutodedios.roperos.ui.screens.login.LoginScreen

/**
 * Controlador de navegación de la aplicación
 */
@Composable
fun RoperosNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RoperosScreens.SettingScreen.name
    ) {
        composable(RoperosScreens.HomeScreen.name) {
            HomeScreen(navController)
        }

        composable(RoperosScreens.SettingScreen.name) {
            val applicationViewModel: ApplicationViewModel = hiltViewModel()
            SettingsScreen(applicationViewModel = applicationViewModel)
        }
    }
}