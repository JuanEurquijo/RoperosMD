package org.minutodedios.roperos.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

/**
 * Las rutas de la barra de navegación inferior deben heredar
 * de esta interfaz
 */
interface IBottomNavigationBarRoute {
    val icon: ImageVector
    val title: String
    val route: String
}

/**
 * Barra de navegación inferior
 */
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    navigationItems: List<IBottomNavigationBarRoute>
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
                            screen.icon,
                            contentDescription = screen.title
                        )
                    },
                    label = { Text(screen.title) }
                )
            }
        }
    }
}