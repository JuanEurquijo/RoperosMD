package org.minutodedios.roperos.ui.screens.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.model.Contribution
import org.minutodedios.roperos.model.Product
import org.minutodedios.roperos.ui.theme.ApplicationTheme
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(
    navController: NavHostController = rememberNavController(),
    products: List<Product>,
    onFinish: (Contribution) -> Unit
) {
    val context = LocalContext.current
    val amount = remember { mutableStateOf(BigDecimal.ZERO) }

    // Calcular el total a pagar
    val total = products.map { it.price }.fold(BigDecimal.ZERO) { c, n -> c + n }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Realizar Aporte") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1.0F))

                // Mostrar monto a Pagar
                Text(
                    text = "Total: ${
                        NumberFormat.getNumberInstance(Locale.getDefault())
                            .format(total)
                    } $",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    modifier = Modifier.padding(32.dp),
                    text = "Por favor digite el monto recibido, el sistema calculará el vuelto....",
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    ),
                )

                Spacer(modifier = Modifier.weight(0.5F))

                // Mostrar ventana de registro de monto
                Text(
                    text = "Monto Recibido: ",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(end = 8.dp)
                )

                InputField(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    valueState = amount,
                    labelId = "",
                    keyboardType = KeyboardType.Number,
                )

                Spacer(modifier = Modifier.weight(1.0F))

                Text(
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp),
                    text = "El cambio a entregar es de\n${
                        NumberFormat.getNumberInstance(Locale.getDefault())
                            .format(calculateExchange(total, amount.value))
                    } $",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp
                    ),
                )

                Spacer(modifier = Modifier.weight(1.0F))

                ElevatedButton(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = {
                        onFinish.invoke(
                            Contribution(
                                total,
                                amount.value,
                                calculateExchange(total, amount.value)
                            )
                        )
                    },
                ) {
                    Text(text = "Guardar Aporte")
                }

                Spacer(modifier = Modifier.weight(1.0F))
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier,
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
        modifier = modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}


fun calculateExchange(total: BigDecimal, amount: BigDecimal): BigDecimal {
    return if (amount < total) {
        BigDecimal.ZERO
    } else {
        amount - total
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryScreenPreview() {
    ApplicationTheme {
        SummaryScreen(
            products = listOf(
                Product("bebés", "camisetas", 5000.0.toBigDecimal(), 2, .10.toBigDecimal()),
                Product("bebés", "pantalones", 8000.0.toBigDecimal(), 2, .05.toBigDecimal()),
                Product("bebés", "camisetas", 5000.0.toBigDecimal(), 2, .0.toBigDecimal()),
            )
        ) {

        }
    }
}