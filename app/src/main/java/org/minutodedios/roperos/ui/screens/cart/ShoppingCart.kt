package org.minutodedios.roperos.ui.screens.cart

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.runBlocking
import org.minutodedios.roperos.model.Category
import org.minutodedios.roperos.navigation.routes.RootNavigationRoutes
import org.minutodedios.roperos.services.database.MockDatabaseService
import org.minutodedios.roperos.ui.theme.ApplicationTheme

enum class ShoppingCartRoutes(
    val route: String
) {
    Initial("initial"),
    AddToCart("addToCart")
}

@Composable
fun ShoppingCart(
    navController: NavHostController = rememberNavController(),
    shoppingCart: MutableList<CartEntry>,
    categories: List<Category>,
) {
    NavHost(
        navController = navController,
        route = RootNavigationRoutes.CartRoute.route,
        startDestination = ShoppingCartRoutes.Initial.route
    ) {
        composable(ShoppingCartRoutes.Initial.route) {
            CartItems(
                navController = navController,
                shoppingCart = shoppingCart,
            )
        }

        composable(ShoppingCartRoutes.AddToCart.route) {
            CartAdd(
                navController = navController,
                categories = categories,
            ) {
                if (shoppingCart.contains(it)) {
                    val item = shoppingCart[shoppingCart.indexOf(it)]

                    // Check if it is possible to add the item and add it
                    if (item.quantity < item.subcategory.quantity) {
                        shoppingCart[shoppingCart.indexOf(it)] =
                            item.copy(quantity = item.quantity + 1)
                    }
                } else {
                    shoppingCart.add(it)
                }
            }
        }
    }
}

@Preview
@Composable
fun ShoppingCartPreview() {
    ApplicationTheme {
        ShoppingCart(
            shoppingCart = mutableListOf(),
            categories = runBlocking { MockDatabaseService().inventoryForLocation("") })
    }
}