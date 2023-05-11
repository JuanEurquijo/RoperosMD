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
import org.minutodedios.roperos.ui.screens.login.LoginScreen
import org.minutodedios.roperos.ui.screens.main.ClosetSelectionScreen
import org.minutodedios.roperos.ui.screens.main.ClothesEntryScreen
import org.minutodedios.roperos.ui.screens.main.InventoryOptionsScreen
import org.minutodedios.roperos.ui.screens.main.InventoryScreen

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
            HomeScreen(authViewModel = hiltViewModel(), navController)
        }

        composable(MainNavigationRoutes.CartScreenRoute.route) {
            CartScreen(navController)
        }

        composable(MainNavigationRoutes.OperationsScreenRoute.route) {
            OperationsScreen()
        }

        composable("clothesEntryScreen") {
            ClothesEntryScreen(navController,databaseViewModel = hiltViewModel())
        }

        composable("inventoryOptionsScreen") {
            InventoryOptionsScreen(navController)
        }

        composable("inventoryScreen") {
            InventoryScreen(navController)
        }

        composable("closetSelectionScreen") {
            ClosetSelectionScreen()
        }
        composable("loginScreen") {
            LoginScreen()
        }

    }
}