package org.minutodedios.roperos.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.model.Subcategory
import org.minutodedios.roperos.ui.theme.ApplicationTheme
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartHome(
    navController: NavHostController,
    shoppingCart: MutableList<CartEntry>,
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Carrito de Compras") })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = if (shoppingCart.isEmpty()) {
                    Arrangement.Center
                } else {
                    Arrangement.Top
                }
            ) {
                if (shoppingCart.isEmpty()) {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Text("No hay elementos")
                } else {
                    shoppingCart.forEach {
                        key(it) {
                            ListItem(
                                headlineText = {
                                    Text(it.subcategory.subcategory.capitalize(Locale.ROOT))
                                },
                                supportingText = { Text(it.category.capitalize(Locale.ROOT)) },
                                overlineText = { Text("Cantidad Disponible: ${it.subcategory.quantity}") },
                                leadingContent = {
                                    FilledIconButton(
                                        onClick = { /*TODO*/ },
                                        colors = IconButtonDefaults.filledTonalIconButtonColors()
                                    ) {
                                        Icon(Icons.Filled.DeleteForever, contentDescription = null)
                                    }
                                },
                                trailingContent = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        FilledIconButton(
                                            onClick = { it.quantity-- /*TODO*/ },
                                            enabled = it.quantity > 0
                                        ) {
                                            Icon(Icons.Filled.Remove, contentDescription = null)
                                        }

                                        Badge(modifier = Modifier.height(32.dp)) {
                                            Text(it.quantity.toString())
                                        }

                                        FilledIconButton(
                                            onClick = { it.quantity++ /*TODO*/ },
                                            enabled = it.quantity < it.subcategory.quantity
                                        ) {
                                            Icon(Icons.Filled.Add, contentDescription = null)
                                        }

                                    }
                                })
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ElevatedButton(onClick = { navController.navigate(ShoppingCartRoutes.AddToCart.route) }) {
                    Icon(Icons.Filled.Add, contentDescription = "Add more")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Agregar Prendas")
                }

                ElevatedButton(onClick = { /*TODO*/ }, enabled = shoppingCart.isNotEmpty()) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Finish")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Finalizar")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShoppingCartHomeEmptyPreview() {
    ApplicationTheme {
        ShoppingCartHome(
            navController = rememberNavController(), shoppingCart = mutableListOf()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ShoppingCartHomeFilledPreview() {
    ApplicationTheme {
        ShoppingCartHome(
            navController = rememberNavController(), shoppingCart = mutableListOf(
                CartEntry("Lorem", Subcategory("Ipsum", 2000.0, 10), 3),
                CartEntry("Ipsum", Subcategory("Dolor", 3000.0, 5), 5)
            )
        )
    }
}