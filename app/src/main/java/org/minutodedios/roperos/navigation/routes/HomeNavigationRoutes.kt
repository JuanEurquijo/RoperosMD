package org.minutodedios.roperos.navigation.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import org.minutodedios.roperos.ui.components.IBottomNavigationBarRoute

/**
 * Enumeración con las pantallas de navegación de la vista principal
 */
enum class HomeNavigationRoutes(
    override val icon: ImageVector,
    override val title: String,
    override val route: String
) : IBottomNavigationBarRoute, Route {
    MenuScreenRoute(Icons.Rounded.Home, "Inicio", "menu"),
    SettingsScreenRoute(Icons.Rounded.Settings, "Configuración", "settings"),
    UserScreenRoute(Icons.Rounded.Person, "Usuario", "user")
}