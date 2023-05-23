package org.minutodedios.roperos.ui.screens.home.actions

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.minutodedios.roperos.model.Category
import org.minutodedios.roperos.navigation.routes.RootNavigationRoute
import org.minutodedios.roperos.services.authentication.MockAuthenticationService
import org.minutodedios.roperos.services.database.MockDatabaseService
import org.minutodedios.roperos.ui.state.AuthViewModel
import org.minutodedios.roperos.ui.state.DatabaseViewModel
import org.minutodedios.roperos.ui.theme.ApplicationTheme
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EntryDetailScreen(
    navController: NavHostController = rememberNavController(),
    databaseViewModel: DatabaseViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    runAsync: Boolean = true,
    category: String
) {
    val context = LocalContext.current
    val user = authViewModel.user.observeAsState()

    var categories: List<Category> by remember {
        mutableStateOf(listOf())
    }

    val quantityMap = remember { mutableStateMapOf<String, Int>() }

    if (user.value != null) {
        if (runAsync) {
            LaunchedEffect(true) {
                categories = withContext(Dispatchers.IO) {
                    val allCategories = databaseViewModel.databaseService.inventoryForLocation(
                        user.value!!.location.id
                    )
                    val filteredCategories = allCategories.filter { it.category == category }
                    filteredCategories
                }
            }
        } else {
            categories = runBlocking {
                val allCategories = databaseViewModel.databaseService.inventoryForLocation(
                    user.value!!.location.id
                )
                val filteredCategories = allCategories.filter { it.category == category }
                filteredCategories
            }
        }
    }

    if (user.value != null && categories.isNotEmpty()) {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(
                    text = "Ingreso de prendas", textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 40.dp)
                )
            }, navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Ir atrás")
                }
            })
        }) { paddingValues ->
            // Columna del scaffold
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                // Cargar lazy
                LazyColumn() {

                    // Columna de los datos
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(30.dp))
                            Text(
                                "Harás ingreso de prendas al ropero: ",
                            )
                            Text(
                                user.value!!.location.name, style = TextStyle(
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )

                            ElevatedButton(
                                modifier = Modifier.padding(16.dp),
                                onClick = {
                                    val job = databaseViewModel.viewModelScope.launch {
                                        quantityMap.forEach { (key, value) ->
                                            databaseViewModel.databaseService.updateSubcategoryQuantity(
                                                category,
                                                user.value!!.location.id,
                                                key,
                                                value
                                            )
                                        }
                                    }
                                    job.invokeOnCompletion {
                                        if (it == null) {
                                            navController.navigate(RootNavigationRoute.HomeRoute.route)
                                            Toast.makeText(
                                                context,
                                                "Ingreso de prendas exitoso",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                },
                            ) {
                                Text(text = "Guardar Datos")
                            }
                        }
                    }

                    // Mostrar cada categoría lazy
                    items(categories) {
                        Card(
                            Modifier
                                .padding(25.dp, 0.dp, 25.dp, 12.dp)
                        ) {
                            // Contenido de la card
                            Column(
                                Modifier.padding(16.dp)
                            ) {
                                Text(
                                    it.category.uppercase(), style = TextStyle(
                                        fontSize = 21.sp, fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentWidth(Alignment.CenterHorizontally)
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Column {
                                    it.subcategories.forEach {
                                        val quantityState = remember {
                                            mutableStateOf(
                                                quantityMap[it.subcategory] ?: 0
                                            )
                                        }
                                        Box(
                                            modifier = Modifier.padding(4.dp)
                                        ) {
                                            ListItem(
                                                modifier = Modifier.clip(CircleShape),
                                                headlineText = {
                                                    Text(text = it.subcategory.replaceFirstChar {
                                                        if (it.isLowerCase()) it.titlecase(
                                                            Locale.getDefault()
                                                        ) else it.toString()
                                                    })
                                                },
                                                trailingContent = {
                                                    Column {
                                                        InputField(
                                                            valueState = quantityState,
                                                            keyboardType = KeyboardType.Number
                                                        )
                                                    }
                                                },
                                            )
                                        }
                                        val previousValue =
                                            remember { mutableStateOf(quantityState.value) }
                                        LaunchedEffect(quantityState.value) {
                                            if (quantityState.value != previousValue.value) {
                                                quantityMap[it.subcategory] =
                                                    it.quantity + quantityState.value
                                                previousValue.value =
                                                    it.quantity + quantityState.value
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } else {
        // Cargando
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<Int>,
    keyboardType: KeyboardType
) {
    val inputValue = remember { mutableStateOf(valueState.value.toString()) }
    OutlinedTextField(
        value = inputValue.value,
        onValueChange = {
            inputValue.value = it
            valueState.value = it.toIntOrNull() ?: 0
        },
        modifier = Modifier
            .width(60.dp)
            .height(48.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
    )
}


@Composable
@Preview(showBackground = true)
fun EntryDetailScreenPreview() {
    ApplicationTheme {
        EntryDetailScreen(
            databaseViewModel = DatabaseViewModel(MockDatabaseService()),
            authViewModel = AuthViewModel(MockAuthenticationService(true)),
            category = "mujeres",
            runAsync = false
        )
    }
}
