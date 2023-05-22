package org.minutodedios.roperos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

        // Mostrar información del usuario
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Información del Usuario
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
                        style = TextStyle(
                            fontSize = 14.sp,
                        )
                    )
                }

                TextButton(
                    onClick = { authViewModel.authService.logout() },
                ) {
                    Text(text = "Cerrar Sesión")
                    Icon(
                        Icons.Filled.WavingHand,
                        contentDescription = "Cerrar Sesión",
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }

            // Mostrar ropero
            LocationCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                location = user.value!!.location
            )

            // Mostrar items del menú
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                ActionsNavigationRoute.values().forEach {
                    Surface(modifier = Modifier.padding(vertical = 8.dp)) {
                        ActionMenuItem(navController = navController, item = it)
                    }
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



