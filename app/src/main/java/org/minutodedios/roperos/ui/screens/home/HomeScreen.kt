package org.minutodedios.roperos.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.navigation.HomeNavigationGraph
import org.minutodedios.roperos.navigation.routes.HomeNavigationRoutes
import org.minutodedios.roperos.ui.components.BottomNavigationBar
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    val navigationItems = HomeNavigationRoutes.values().map { it }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController, navigationItems) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HomeNavigationGraph(navController = navController)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    ApplicationTheme {
        HomeScreen()
    }
}