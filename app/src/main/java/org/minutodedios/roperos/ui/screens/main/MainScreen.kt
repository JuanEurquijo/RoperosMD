package org.minutodedios.roperos.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.ui.navigation.BottomNavigationBar
import org.minutodedios.roperos.ui.navigation.MainNavigation
import org.minutodedios.roperos.ui.navigation.main.MainNavigationRoutes
import org.minutodedios.roperos.ui.navigation.main.MainNavigationRoutes.*
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navigationItems = MainNavigationRoutes.values()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController, navigationItems.map { it }) }
    ) {
        MainNavigation(navController = navController)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    ApplicationTheme {
        MainScreen()
    }
}