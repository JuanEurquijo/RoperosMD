package org.minutodedios.roperos.ui.screens.home.actions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.minutodedios.roperos.model.Category
import org.minutodedios.roperos.services.authentication.MockAuthenticationService
import org.minutodedios.roperos.services.database.MockDatabaseService
import org.minutodedios.roperos.ui.screens.cart.CartEntry
import org.minutodedios.roperos.ui.screens.cart.ShoppingCart
import org.minutodedios.roperos.ui.state.AuthViewModel
import org.minutodedios.roperos.ui.state.DatabaseViewModel
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@Composable
fun SalesScreen(
    navController: NavHostController,
    databaseViewModel: DatabaseViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    runAsync: Boolean = true
) {
    val user = authViewModel.user.observeAsState()

    var categories: List<Category> by remember {
        mutableStateOf(listOf())
    }

    val shoppingCart: MutableList<CartEntry> = remember {
        mutableStateListOf()
    }

    if (user.value != null) {
        if (runAsync) {
            LaunchedEffect(true) {
                categories =
                    withContext(Dispatchers.IO) {
                        databaseViewModel.databaseService.inventoryForLocation(
                            user.value!!.location.id
                        )
                    }
            }
        } else {
            categories = runBlocking {
                databaseViewModel.databaseService.inventoryForLocation(
                    user.value!!.location.id
                )
            }
        }
    }

    if (user.value != null && categories.isNotEmpty()) {
        ShoppingCart(
            shoppingCart = shoppingCart,
            categories = categories
        )
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SalesScreenPreview() {
    ApplicationTheme {
        SalesScreen(
            navController = rememberNavController(),
            databaseViewModel = DatabaseViewModel(MockDatabaseService()),
            authViewModel = AuthViewModel(MockAuthenticationService(true)),
            runAsync = false
        )
    }
}