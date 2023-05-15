package org.minutodedios.roperos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.navigation.routes.RootNavigationRoutes
import org.minutodedios.roperos.ui.screens.home.HomeScreen

/**
 * Controlador de navegación de la aplicación
 */
@Composable
fun RootNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = RootNavigationRoutes.RootRoute.route,
        startDestination = RootNavigationRoutes.HomeRoute.route,
    ) {
        composable(RootNavigationRoutes.HomeRoute.route) {
            HomeScreen()
        }
    }
}