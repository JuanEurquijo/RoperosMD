package org.minutodedios.roperos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.minutodedios.roperos.model.Product
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@Composable
fun ProductCard(modifier: Modifier = Modifier, product: Product) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.category.uppercase())
            Text(text = product.subcategory.capitalize(), style = TextStyle(fontSize = 24.sp))

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Cantidad: ")
                    }
                    append(product.ammount.toString())
                }
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Precio Unidad: ")
                    }
                    append(product.unitaryPrice.toPlainString())
                }
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Descuento: ")
                    }
                    append("${(product.discount * 100.0.toBigDecimal()).toDouble()} %")
                }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Total: ")
                    }
                    append(product.price.toPlainString())
                },
                style = TextStyle(fontSize = 18.sp)
            )
        }
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    ApplicationTheme {
        ProductCard(
            product = Product(
                "mujeres",
                "vestidos de Boda",
                3000.0.toBigDecimal(),
                40,
                0.10.toBigDecimal()
            )
        )
    }
}