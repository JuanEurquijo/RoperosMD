package org.minutodedios.roperos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.navigation.routes.ActionsNavigationRoute
import org.minutodedios.roperos.navigation.routes.RootNavigationRoute
import org.minutodedios.roperos.ui.screens.FallbackScreen
import org.minutodedios.roperos.ui.screens.HomeScreen
import org.minutodedios.roperos.ui.screens.home.actions.InventoryScreen
import org.minutodedios.roperos.ui.screens.home.actions.SalesScreen

/**
 * Controlador de navegación de la aplicación
 */
@Composable
fun RootNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = RootNavigationRoute.RootRoute.route,
        startDestination = RootNavigationRoute.HomeRoute.route,
    ) {
        /* -- Main -- */

        composable(RootNavigationRoute.HomeRoute.route) {
            HomeScreen(navController = navController)
        }

        /* -- Actions -- */

        composable(ActionsNavigationRoute.InventoryRoute.route) {
            InventoryScreen(navController = navController)
        }

        composable(ActionsNavigationRoute.OutRoute.route) {
            SalesScreen(navController = navController)
        }

        /* -- Others -- */
        composable("{screenNotFound}") {
            FallbackScreen()
        }
    }
}