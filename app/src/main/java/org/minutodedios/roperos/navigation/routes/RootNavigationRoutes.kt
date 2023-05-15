package org.minutodedios.roperos.navigation.routes

enum class RootNavigationRoutes(override val route: String) : Route {
    RootRoute("/"),
    HomeRoute("home"),
    CartRoute("cart"),
}