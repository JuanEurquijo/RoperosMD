package org.minutodedios.roperos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.navigation.routes.ActionsNavigationRoutes
import org.minutodedios.roperos.navigation.routes.HomeNavigationRoutes
import org.minutodedios.roperos.navigation.routes.RootNavigationRoutes
import org.minutodedios.roperos.ui.screens.home.MenuScreen
import org.minutodedios.roperos.ui.screens.home.SettingsScreen
import org.minutodedios.roperos.ui.screens.home.UserScreen
import org.minutodedios.roperos.ui.screens.home.actions.InventoryScreen
import org.minutodedios.roperos.ui.screens.home.actions.SalesScreen

@Composable
fun HomeNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = RootNavigationRoutes.HomeRoute.route,
        startDestination = HomeNavigationRoutes.MenuScreenRoute.route
    ) {

        /* -- Navigation Bar -- */

        composable(HomeNavigationRoutes.MenuScreenRoute.route) {
            MenuScreen(navController = navController)
        }

        composable(HomeNavigationRoutes.SettingsScreenRoute.route) {
            SettingsScreen()
        }

        composable(HomeNavigationRoutes.UserScreenRoute.route) {
            UserScreen()
        }

        /* -- TODO: Additional routes should not show BottomNavigationBar -- */

        composable(ActionsNavigationRoutes.InventoryRoute.route) {
            InventoryScreen(navController = navController)
        }

        composable(ActionsNavigationRoutes.SalesRoute.route) {
            SalesScreen(navController = navController)
        }
    }
}


