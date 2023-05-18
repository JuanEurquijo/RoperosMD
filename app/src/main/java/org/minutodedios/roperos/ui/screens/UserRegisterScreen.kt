package org.minutodedios.roperos.ui.screens

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
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegisterScreen(navController: NavHostController) {

    val name = rememberSaveable { mutableStateOf("") }
    val lastname = rememberSaveable { mutableStateOf("") }
    val identifier = rememberSaveable { mutableStateOf("") }
    val phone = rememberSaveable { mutableStateOf("") }

    val expanded = remember { mutableStateOf(false) }
    val idTypes = listOf("Tarjeta de Identidad","Cédula de Ciudadanía","Cédula de Extranjería","Pasaporte")
    val selectedText = rememberSaveable {mutableStateOf("") }
    val textfieldSize  = rememberSaveable{mutableStateOf(0)}
    val icon = if (expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown


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
                value = selectedText.value,
                onValueChange = { selectedText.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .onGloballyPositioned { coordinates ->
                        textfieldSize.value = coordinates.size.width
                    },
                label = { Text("Tipo de documento") },
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { expanded.value = !expanded.value })
                }
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textfieldSize.value.toDp()})
            ) {
                idTypes.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedText.value = label
                        expanded.value = false
                    }, text = { Text(text = label) })
                }
            }
        }

        InputField(
            valueState = identifier,
            labelId = "Cédula",
            keyboardType = KeyboardType.Number
        )

        InputField(
            valueState = phone,
            labelId = "Teléfono",
            keyboardType = KeyboardType.Phone
        )

        ElevatedButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {  },
        ) {
            Text(text = "Realizar aporte")
        }

        ElevatedButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {  },
            colors = ButtonDefaults.buttonColors(Color.LightGray)
        ) {
            Text(text = "Omitir",)
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
