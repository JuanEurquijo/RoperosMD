package org.minutodedios.roperos.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
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
import org.minutodedios.roperos.services.authentication.MockAuthenticationService
import org.minutodedios.roperos.ui.components.LocationCard
import org.minutodedios.roperos.ui.state.AuthViewModel
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@Composable
fun UserScreen(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val user = authViewModel.user.observeAsState()

    if (user.value == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido\n${user.value!!.fullname}",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                ),
            )
            Text(
                text = user.value!!.email,
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            LocationCard(location = user.value!!.location)

            Spacer(modifier = Modifier.height(10.dp))
            ElevatedButton(onClick = { authViewModel.authService.logout() }) {
                Icon(
                    Icons.Filled.Logout,
                    contentDescription = "CerrarSesión"
                )
                Text(text = "Cerrar Sesión")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserScreenPreview() {
    ApplicationTheme {
        UserScreen(
            AuthViewModel(
                MockAuthenticationService(
                    initAsAuthenticated = true
                )
            )
        )
    }
}