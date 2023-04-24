package org.minutodedios.roperos.ui.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    navigationItems: List<INavigationRoute>
) {
    BottomAppBar {
        NavigationBar {
            val currentRoute by navController.currentBackStackEntryAsState()

            navigationItems.forEach { screen ->
                NavigationBarItem(
                    selected = currentRoute?.destination?.route == screen.route,
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