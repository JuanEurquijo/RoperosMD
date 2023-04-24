package org.minutodedios.roperos.ui.navigation.main

import org.minutodedios.roperos.R
import org.minutodedios.roperos.ui.navigation.INavigationRoute

enum class MainNavigationRoutes(
    override val icon: Int,
    override val title: String,
    override val route: String
) : INavigationRoute {
    HomeScreenRoute(R.drawable.icon_home, "Inicio", "homeScreen"),
    CartScreenRoute(R.drawable.icon_cart, "Carrito", "cartScreen"),
    OperationsScreenRoute(R.drawable.icon_menu, "Operaciones", "operationsScreen"),
    SettingsScreenRoute(R.drawable.icon_settings, "Ajustes", "settingsScreen")
}
