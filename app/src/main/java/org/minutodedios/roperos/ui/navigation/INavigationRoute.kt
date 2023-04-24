package org.minutodedios.roperos.ui.navigation

/**
 * Interfaz que representa una ruta de navegación
 * Debe tener un entero a un drawable que representará su ícono,
 * un título para mostrar y una ruta
 */
interface INavigationRoute {
    val icon: Int
    val title: String
    val route: String
}