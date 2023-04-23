package org.minutodedios.roperos.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.screens.home.Cart
import org.minutodedios.roperos.screens.home.Home
import org.minutodedios.roperos.screens.home.ItemsBottomNav
import org.minutodedios.roperos.screens.home.ItemsBottomNav.*
import org.minutodedios.roperos.screens.home.Operations

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen1.route,
    ) {
        composable(Screen1.route) {
            Home()
        }
        composable(Screen2.route) {
            Cart()
        }
        composable(Screen3.route) {
            Operations()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    val navigation_items = listOf(
        Screen1,
        Screen2,
        Screen3
    )

    Scaffold(
        bottomBar = { BottomNav(navController, navigation_items) }
    ) {
        NavigationHost(navController = navController)
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}

@Composable
fun BottomNav(
    navController: NavHostController,
    navigationItems: List<ItemsBottomNav>
) {
    BottomAppBar() {
        NavigationBar() {
            val currentRoute = currentRoute(navController = navController)
            navigationItems.forEach { screen ->
                NavigationBarItem(
                    selected = currentRoute == screen.route,
                    onClick = { navController.navigate(screen.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = screen.title
                        )
                    },
                    label = { Text(screen.title) }
                )
            }
        }
    }
}