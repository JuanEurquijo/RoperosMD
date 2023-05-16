package org.minutodedios.roperos.navigation.routes

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import org.minutodedios.roperos.R

/**
 * Acciones del menú principal
 */
enum class ActionsNavigationRoute(
    val title: String,
    val background: Color,
    val foreground: Color,
    val route: String,
    @DrawableRes val icon: Int,
) {
    /**
     * Ruta para mostrar el ingreso de nuevas prendas
     */
    InRoute(
        "Ingreso de Prendas",
        Color(0xffa5c8ff),
        Color(0xff00315e),
        "in",
        R.drawable.hand,
    ),

    /**
     * Ruta para realizar la venta de prendas
     */
    OutRoute(
        "Salida de Prendas",
        Color(0xffd4e3ff),
        Color(0xff004785),
        "out",
        R.drawable.clothes
    ),

    /**
     * Ruta para mostrar el inventario
     */
    InventoryRoute(
        "Inventario",
        Color(0xffdabde2),
        Color(0xff3d2946),
        "inventory",
        R.drawable.drawer
    ),

    /**
     * Mostrar el consolidado de ventas
     */
    HistoryRoute(
        "Historial",
        Color(0xffd8e3f8),
        Color(0xff3d4758),
        "history",
        R.drawable.book
    ),
}