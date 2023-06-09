package org.minutodedios.roperos.ui.screens.home.actions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.minutodedios.roperos.model.Category
import org.minutodedios.roperos.services.authentication.MockAuthenticationService
import org.minutodedios.roperos.services.database.MockDatabaseService
import org.minutodedios.roperos.ui.state.AuthViewModel
import org.minutodedios.roperos.ui.state.DatabaseViewModel
import org.minutodedios.roperos.ui.theme.ApplicationTheme
import java.util.Locale

@Composable
@OptIn(
    ExperimentalMaterial3Api::class
)
fun InventoryDetailScreen(
    navController: NavHostController,
    databaseViewModel: DatabaseViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    runAsync: Boolean = true,
    category: String
) {
    val user = authViewModel.user.observeAsState()

    var categories: List<Category> by remember {
        mutableStateOf(listOf())
    }

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
            TopAppBar(title = { Text(text = "Inventario", modifier = Modifier
                .fillMaxWidth()
                .padding(90.dp,30.dp,0.dp,0.dp) )
            },navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Ir atrás")
                }
            })
        }) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                Column(modifier = Modifier.padding(32.dp)) {
                    Text(
                        "Estas consultando el inventario del ropero:",
                    )
                    Text(
                        user.value!!.location.name, style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }


                categories.forEach {
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(25.dp,0.dp,25.dp,12.dp)
                    ) {
                        Column(
                            Modifier.padding(16.dp)
                        ) {
                            Text(
                                it.category.uppercase(), style = TextStyle(
                                    fontSize = 21.sp, fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            LazyColumn {
                                items(it.subcategories) {
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
                                                    Text(text = "Cantidad: ${it.quantity}")
                                                    Text(text = "Precio: $${it.price}")
                                                }
                                            },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InventoryDetailScreenPreview() {
    ApplicationTheme {
        InventoryScreen(
            rememberNavController(),
            DatabaseViewModel(MockDatabaseService()),
            AuthViewModel(MockAuthenticationService(true)),
            runAsync = false
        )
    }
}