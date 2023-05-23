package org.minutodedios.roperos.ui.screens.cart

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.runBlocking
import org.minutodedios.roperos.model.Category
import org.minutodedios.roperos.model.Client
import org.minutodedios.roperos.model.Contribution
import org.minutodedios.roperos.model.Product
import org.minutodedios.roperos.navigation.RootNavigationGraph
import org.minutodedios.roperos.navigation.routes.RootNavigationRoute
import org.minutodedios.roperos.services.database.MockDatabaseService
import org.minutodedios.roperos.ui.screens.HomeScreen
import org.minutodedios.roperos.ui.theme.ApplicationTheme

internal sealed class ShoppingCartRoutes(
    val route: String
) {
    object Initial : ShoppingCartRoutes("initial")
    object AddToCart : ShoppingCartRoutes("addToCart")
    object CartDiscount : ShoppingCartRoutes("cartDiscount/{indexOfItem}") {
        fun routeOfIndex(index: Int): String = "cartDiscount/$index"
    }

    object UserRegister : ShoppingCartRoutes("user")
    object Summary : ShoppingCartRoutes("summary")
}

@Composable
fun ShoppingCart(
    navController: NavHostController = rememberNavController(),
    shoppingCart: MutableList<CartEntry>,
    categories: List<Category>,
    onComplete: (List<Product>, Client?, Contribution) -> Unit,
) {
    var products: List<Product> = listOf()
    var client: Client? = null

    NavHost(
        navController = navController,
        route = RootNavigationRoute.CartRoute.route,
        startDestination = ShoppingCartRoutes.Initial.route
    ) {
        composable(ShoppingCartRoutes.Initial.route) {
            CartItems(
                navController = navController,
                shoppingCart = shoppingCart,
            ) {
                // Productos
                products = shoppingCart.map { Product(it) }

                // Go to user route
                navController.navigate(ShoppingCartRoutes.UserRegister.route)
            }
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

        composable(
            ShoppingCartRoutes.CartDiscount.route,
            arguments = listOf(navArgument("indexOfItem") { type = NavType.IntType })
        ) {
            val entry = it.arguments?.getInt("indexOfItem");
            if (entry != null && shoppingCart.size > entry) {
                CartDiscount(
                    navHostController = navController,
                    product = Product(shoppingCart[entry])
                ) { discount ->
                    // Copy with new discount value
                    shoppingCart[entry] = shoppingCart[entry].copy(discount = discount)
                }
            }
        }

        composable(ShoppingCartRoutes.UserRegister.route) {
            ClientRegisterScreen(navController = navController) {
                // Configurar el cliente
                client = it

                //TODO: Ventana de ventas
            }
        }

        composable(ShoppingCartRoutes.Summary.route) {
            SummaryScreen(
                navController = navController,
                products = products)
        }

        composable(RootNavigationRoute.HomeRoute.route) {
           RootNavigationGraph()
        }
    }
}


@Preview
@Composable
fun ShoppingCartPreview() {
    ApplicationTheme {
        ShoppingCart(
            shoppingCart = mutableListOf(),
            categories = runBlocking { MockDatabaseService().inventoryForLocation("") }) { _, _, _ ->
        }
    }
}