package org.minutodedios.roperos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.screens.home.CartScreen
import org.minutodedios.roperos.screens.home.HomeScreen
import org.minutodedios.roperos.screens.home.OperationsScreen
import org.minutodedios.roperos.ui.navigation.main.MainNavigationRoutes
import org.minutodedios.roperos.ui.screens.main.SettingsScreen

/**
 * Controlador de navegación de la aplicación
 */
@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = MainNavigationRoutes.HomeScreenRoute.route
    ) {
        composable(MainNavigationRoutes.HomeScreenRoute.route) {
            HomeScreen()
        }

        composable(MainNavigationRoutes.CartScreenRoute.route) {
            CartScreen()
        }

        composable(MainNavigationRoutes.OperationsScreenRoute.route) {
            OperationsScreen()
        }

        composable(MainNavigationRoutes.SettingsScreenRoute.route) {
            SettingsScreen(authViewModel = hiltViewModel())
        }
    }
}