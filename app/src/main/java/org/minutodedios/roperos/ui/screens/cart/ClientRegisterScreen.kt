package org.minutodedios.roperos.ui.screens.cart

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.model.Client
import org.minutodedios.roperos.navigation.routes.RootNavigationRoute
import org.minutodedios.roperos.ui.theme.ApplicationTheme

sealed class IdTypes(val showName: String, val storeName: String) {
    object CC : IdTypes("Cédula de Ciudadanía", "CC")
    object TI : IdTypes("Tarjeta de Identidad", "TI")
    object CE : IdTypes("Cédula de Extranjería", "CE")
    object PASSPORT : IdTypes("Pasaporte", "Pasaporte")
    object PHONE : IdTypes("Telefono", "Telefono")

    companion object {
        val allTypes: List<IdTypes>
            get() = listOf(CC, TI, CE, PASSPORT, PHONE)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientRegisterScreen(
    navController: NavHostController = rememberNavController(),
    onComplete: (Client?) -> Unit
) {
    val name = remember { mutableStateOf("") }
    val lastname = remember { mutableStateOf("") }
    val identifier = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var selectedIdType: IdTypes? by remember { mutableStateOf(null) }
    val textfieldSize = remember { mutableStateOf(0) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Registrar Aportante",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
            ), fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(60.dp))

        InputField(
            valueState = name,
            labelId = "Nombre",
            keyboardType = KeyboardType.Text
        )

        InputField(
            valueState = lastname,
            labelId = "Apellidos",
            keyboardType = KeyboardType.Text
        )

        Column(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedIdType?.showName ?: "",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .onGloballyPositioned { coordinates ->
                        textfieldSize.value = coordinates.size.width
                    },
                label = { Text("Tipo de documento") },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                },
                readOnly = true
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.value.toDp() })
            ) {
                IdTypes.allTypes.forEach { entry ->
                    DropdownMenuItem(onClick = {
                        selectedIdType = entry
                        expanded = false
                    }, text = { Text(text = entry.showName) })
                }
            }
        }

        InputField(
            valueState = identifier,
            labelId = "Número de documento",
            keyboardType = if (selectedIdType is IdTypes.PASSPORT) KeyboardType.Text else KeyboardType.Number
        )

        InputField(
            valueState = phone,
            labelId = "Teléfono",
            keyboardType = KeyboardType.Phone
        )

        ElevatedButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                // Invoke with client
                onComplete.invoke(
                    Client(
                        name.value,
                        lastname.value,
                        phone.value,
                        selectedIdType!!.storeName,
                        identifier.value
                    )
                )
                navController.popBackStack()
            },
        ) {
            Text(text = "Realizar aporte")
        }

        ElevatedButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                // Invoke with no client
                onComplete.invoke(null)
                navController.navigate(ShoppingCartRoutes.Summary.route)
            },
            colors = ButtonDefaults.buttonColors(Color.LightGray)
        ) {
            Text(text = "Omitir")
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ClientRegisterScreenPreview() {
    ApplicationTheme {
        ClientRegisterScreen {

        }
    }
}
