package org.minutodedios.roperos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.screens.StartScreen
import org.minutodedios.roperos.screens.home.HomeScreen
import org.minutodedios.roperos.screens.login.LoginScreen

@Composable
fun RoperosNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RoperosScreens.StartScreen.name
    ){
        composable(RoperosScreens.StartScreen.name){
            StartScreen(navController = navController)
        }
        composable(RoperosScreens.LoginScreen.name){
            LoginScreen(navController = navController)
        }
        composable(RoperosScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
    }
}