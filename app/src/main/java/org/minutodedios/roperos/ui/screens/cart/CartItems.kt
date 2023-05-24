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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.model.Subcategory
import org.minutodedios.roperos.ui.theme.ApplicationTheme
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CartItems(
    navController: NavHostController,
    shoppingCart: MutableList<CartEntry>,
    onFinish: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Carrito de Compras", textAlign = TextAlign.Center
            )
        })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = if (shoppingCart.isEmpty()) {
                    Arrangement.Center
                } else {
                    Arrangement.Top
                },
            ) {
                if (shoppingCart.isEmpty()) {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Text("No hay elementos")
                } else {
                    shoppingCart.forEachIndexed { index, item ->
                        ListItem(modifier = Modifier.padding(0.dp),
                            overlineText = { Text("Disponibles: ${item.subcategory.quantity}") },
                            headlineText = {
                                Text("(${item.category.capitalize()}) ${item.subcategory.subcategory.capitalize()}")
                            },
                            supportingText = {
                                Column {
                                    Text("Unidad: ${item.subcategory.price.toPlainString()}")
                                    if (item.discount > BigDecimal.ZERO) {
                                        Text(
                                            text = "Descuento: ${(item.discount * 100.0.toBigDecimal()).toInt()}%",
                                            style = TextStyle(
                                                color = MaterialTheme.colorScheme.secondary,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                    Text(
                                        "Total: ${item.price.toPlainString()}",
                                        style = TextStyle(fontWeight = FontWeight.Bold)
                                    )
                                }
                            },
                            leadingContent = {
                                Row {
                                    FilledIconButton(
                                        colors = IconButtonDefaults.filledTonalIconButtonColors(),
                                        onClick = {
                                            shoppingCart.remove(item)
                                        },
                                    ) {
                                        Icon(
                                            Icons.Filled.DeleteForever,
                                            contentDescription = "Eliminar producto"
                                        )
                                    }

                                    // Discount
                                    FilledIconButton(
                                        colors = IconButtonDefaults.filledIconButtonColors(
                                            containerColor = MaterialTheme.colorScheme.secondary,
                                        ),
                                        onClick = {
                                            navController.navigate(
                                                ShoppingCartRoutes.CartDiscount.routeOfIndex(
                                                    index
                                                )
                                            )
                                        },
                                    ) {
                                        Icon(
                                            Icons.Filled.Percent,
                                            contentDescription = "Agregar descuento"
                                        )
                                    }
                                }

                            },
                            trailingContent = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    FilledIconButton(
                                        onClick = {
                                            // Remove one Item
                                            shoppingCart[index] =
                                                item.copy(quantity = item.quantity - 1)
                                        }, enabled = item.quantity > 1
                                    ) {
                                        Icon(
                                            Icons.Filled.Remove, contentDescription = "Eliminar uno"
                                        )
                                    }

                                    Badge(modifier = Modifier.height(32.dp)) {
                                        Text(item.quantity.toString())
                                    }

                                    FilledIconButton(
                                        onClick = {
                                            // Add one item
                                            shoppingCart[index] =
                                                item.copy(quantity = item.quantity + 1)
                                        }, enabled = item.quantity < item.subcategory.quantity
                                    ) {
                                        Icon(Icons.Filled.Add, contentDescription = "Agregar otro")
                                    }
                                }
                            })
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

                ElevatedButton(
                    onClick = {
                        onFinish.invoke()
                    },
                    enabled = shoppingCart.isNotEmpty()
                ) {
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
        CartItems(
            navController = rememberNavController(),
            shoppingCart = mutableListOf(),
            onFinish = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ShoppingCartHomeFilledPreview() {
    ApplicationTheme {
        CartItems(
            navController = rememberNavController(), shoppingCart = mutableListOf(
                CartEntry(
                    "Lorem", Subcategory("Ipsum", 2000.0.toBigDecimal(), 10), 3, 0.25.toBigDecimal()
                ),
                CartEntry("Ipsum", Subcategory("Dolor", 3000.0.toBigDecimal(), 5), 5),
                CartEntry("Ipsum", Subcategory("Dolor", 3000.0.toBigDecimal(), 5), 5),
                CartEntry("Ipsum", Subcategory("Dolor", 3000.0.toBigDecimal(), 5), 5),
                CartEntry("Ipsum", Subcategory("Dolor", 3000.0.toBigDecimal(), 5), 5),
                CartEntry("Ipsum", Subcategory("Dolor", 3000.0.toBigDecimal(), 5), 5),
                CartEntry("Ipsum", Subcategory("Dolor", 3000.0.toBigDecimal(), 5), 5),
                CartEntry("Ipsum", Subcategory("Dolor", 3000.0.toBigDecimal(), 5), 5),
                CartEntry("Ipsum", Subcategory("Dolor", 3000.0.toBigDecimal(), 5), 5),
            ),
            onFinish = {}
        )
    }
}