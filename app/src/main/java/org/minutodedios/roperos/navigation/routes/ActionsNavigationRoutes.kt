package org.minutodedios.roperos.navigation.routes

import androidx.compose.ui.graphics.Color

/**
 * Acciones del menú principal
 */
enum class ActionsNavigationRoutes(
    val title: String,
    val gradient: List<Color>,
    override val route: String,
) : Route {
    /**
     * Ruta para realizar la venta de prendas
     */
    SalesRoute(
        "Venta de prendas",
        listOf(Color(0xFF7BDFFF), Color(0xFFFFFFFF)),
        "sales"
    ),

    /**
     * Ruta para mostrar el ingreso de nuevas prendas
     */
    RegistrationRoute(
        "Registrar ingreso",
        listOf(Color(0XFF9FFCC4), Color(0xFFFFFFFF)),
        "registration"
    ),

    /**
     * Mostrar el consolidado de ventas
     */
    HistoryRoute(
        "Consolidado de ventas",
        listOf(Color(0xFFFFC9E9), Color(0xFFFFFFFF)),
        "history"
    ),

    /**
     * Ruta para mostrar el inventario
     */
    InventoryRoute(
        "Inventario",
        listOf(Color(0xFFE84A5F), Color(0xFFFFFFFF)),
        "inventory"
    )
}