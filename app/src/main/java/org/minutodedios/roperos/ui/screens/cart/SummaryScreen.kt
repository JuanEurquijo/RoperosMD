package org.minutodedios.roperos.ui.screens.cart

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.minutodedios.roperos.model.Product
import org.minutodedios.roperos.navigation.routes.RootNavigationRoute
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale


@Composable
fun SummaryScreen(
    navController: NavHostController,
    products: List<Product>
) {
    val context = LocalContext.current
    val amount = remember { mutableStateOf(BigDecimal.ZERO) }
    val total = calculateTotal(products)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Realizar Aporte",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Total: $ " + NumberFormat.getNumberInstance(Locale.getDefault()).format(total),
                fontSize = 18.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Left
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Monto",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(end = 50.dp)
                )
                InputField(
                    valueState = amount,
                    labelId = "",
                    keyboardType = KeyboardType.Number,
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Tu cambio es:  $" +  NumberFormat.getNumberInstance(Locale.getDefault()).format(calculateExchange(total, amount.value)),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold
                ),
                fontSize = 18.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            ElevatedButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    navController.navigate(RootNavigationRoute.HomeRoute.route)
                    Toast.makeText(context, "Aporte Exitoso", Toast.LENGTH_SHORT).show()
                          },
            ) {
                Text(text = "Guardar Aporte")
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<BigDecimal>,
    labelId: String,
    keyboardType: KeyboardType
) {
    val inputValue = remember { mutableStateOf(valueState.value.toString()) }
    OutlinedTextField(
        value = inputValue.value,
        onValueChange = { newValue ->
            inputValue.value = newValue
            valueState.value = try {
                BigDecimal(newValue)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
        },
        label = { Text(text = labelId) },
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}


fun calculateTotal(products: List<Product>): BigDecimal{
    var total = BigDecimal.ZERO
    for (product in products) {
        total = total.add(product.price)
    }
    return total
}

fun calculateExchange(total: BigDecimal, amount: BigDecimal): BigDecimal {
    if (amount < total) {
        return BigDecimal.ZERO
    } else {
        return amount - total
    }
}