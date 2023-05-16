package org.minutodedios.roperos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.navigation.routes.ActionsNavigationRoute
import org.minutodedios.roperos.services.authentication.MockAuthenticationService
import org.minutodedios.roperos.ui.components.ActionMenuItem
import org.minutodedios.roperos.ui.components.LocationCard
import org.minutodedios.roperos.ui.state.AuthViewModel
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val user = authViewModel.user.observeAsState()

    if (user.value == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {

        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(32.dp), horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Show user information
                    Column {
                        Text(
                            text = "Bienvenido\n${user.value!!.fullname}",
                            style = TextStyle(
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Left,
                            ),
                        )
                        Text(
                            text = user.value!!.email,
                        )
                    }

                    // Show location card
                    LocationCard(location = user.value!!.location)
                }

                ElevatedButton(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = { authViewModel.authService.logout() },
                ) {
                    Text(text = "Cerrar Sesión")
                }
            }

            // Mostrar las opciones
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(ActionsNavigationRoute.values()) {
                    ActionMenuItem(navController = navController, item = it)
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    ApplicationTheme {
        HomeScreen(
            navController = rememberNavController(),
            authViewModel = AuthViewModel(MockAuthenticationService(true)),
        )
    }
}



