package org.minutodedios.roperos.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.model.Product
import org.minutodedios.roperos.ui.components.ProductCard
import org.minutodedios.roperos.ui.theme.ApplicationTheme
import java.math.BigDecimal

/// CartDiscount ammount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartDiscount(
    navHostController: NavHostController = rememberNavController(),
    product: Product,
    onDiscountApply: (BigDecimal) -> Unit
) {
    val discountAmmount = 0.05.toBigDecimal()

    // Current discount
    var modifiedProduct by remember { mutableStateOf(product) }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Agregar descuento") })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            // Discount selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                FilledIconButton(
                    onClick = {
                        modifiedProduct =
                            modifiedProduct.copy(discount = modifiedProduct.discount - discountAmmount)
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    enabled = modifiedProduct.discount > BigDecimal.ZERO
                ) {
                    Icon(Icons.Filled.Remove, contentDescription = "Remove disccount")
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .padding(24.dp)
                ) {
                    Text(
                        text = "${(modifiedProduct.discount * 100.toBigDecimal()).toInt()}%",
                        style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold)
                    )
                }
                FilledIconButton(
                    onClick = {
                        modifiedProduct =
                            modifiedProduct.copy(discount = modifiedProduct.discount + discountAmmount)
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    enabled = modifiedProduct.discount < BigDecimal.ONE, /* TODO: Add discount limit */
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Remove disccount")
                }
            }


            // Product Information
            Text(
                modifier = Modifier.padding(horizontal = 18.dp), text = "Información del Producto:"
            )
            ProductCard(modifier = Modifier.padding(16.dp), product = modifiedProduct)

            // Accept Changes
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Cancel
                ElevatedButton(
                    onClick = { navHostController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.error,
                    )
                ) {
                    Text(text = "Cancelar")
                }

                // Add discount
                ElevatedButton(
                    onClick = {
                        onDiscountApply.invoke(modifiedProduct.discount)
                        navHostController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.secondary,
                    )
                ) {
                    Text(text = "Agregar Descuento")
                }
            }
        }
    }
}

@Composable
@Preview
fun CartDiscountPreview() {
    ApplicationTheme {
        CartDiscount(
            product = Product("Lorem", "Ipsum", 1000.0.toBigDecimal(), 10, 0.05.toBigDecimal())
        ) {}
    }
}