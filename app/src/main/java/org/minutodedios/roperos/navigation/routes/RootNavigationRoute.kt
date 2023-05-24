package org.minutodedios.roperos.navigation.routes

enum class RootNavigationRoute(val route: String) {
    RootRoute("/"),
    HomeRoute("home"),
    CartRoute("cart"),
    UserRoute("user"),
    InventoryDetailRoute("inventoryDetails"),
    EntryDetailRoute("entryDetails")
}