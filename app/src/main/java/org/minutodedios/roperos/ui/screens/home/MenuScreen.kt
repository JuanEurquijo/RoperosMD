package org.minutodedios.roperos.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.R
import org.minutodedios.roperos.navigation.routes.ActionsNavigationRoutes
import org.minutodedios.roperos.services.authentication.MockAuthenticationService
import org.minutodedios.roperos.ui.state.AuthViewModel
import org.minutodedios.roperos.ui.theme.ApplicationTheme


interface IActionRoute {
    val title: String
    val gradient: List<Color>
    val route: String
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Menú Principal",
                modifier = Modifier.padding(30.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                ), fontSize = 18.sp
            )
        }

        items(ActionsNavigationRoutes.values()) {
            Card(
                onClick = {
                    navController.navigate(it.route)
                },
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .size(width = 340.dp, height = 180.dp)
                    .background(Color.Transparent)
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(brush = Brush.verticalGradient(it.gradient))
                ) {
                    Text(
                        text = it.title,
                        Modifier
                            .padding(8.dp, 20.dp, 8.dp, 0.dp)
                            .align(Alignment.TopCenter),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier
                            .size(width = 400.dp, height = 130.dp)
                            .align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    ApplicationTheme {
        MenuScreen(
            authViewModel = AuthViewModel(MockAuthenticationService()),
            navController = rememberNavController()
        )
    }
}



