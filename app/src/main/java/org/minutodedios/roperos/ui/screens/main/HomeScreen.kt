package org.minutodedios.roperos.screens.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.minutodedios.roperos.AuthViewModel
import org.minutodedios.roperos.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    authViewModel: AuthViewModel = viewModel(),
    navController: NavHostController
){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(
        Icons.Default.Settings to "Ajustes",
        Icons.Default.Logout to "Cerrar Sesión")
    var isSelected by remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateOf(items[0].first) }

    val names = listOf(
        "Venta de Prendas",
        "Ingreso de Prendas",
        "Consolidado de Ventas",
        "Inventario"
    )
    val colors = listOf(
        listOf(Color(0xFF7BDFFF), Color(0xFFFFFFFF)),
        listOf(Color(0XFF9FFCC4), Color(0xFFFFFFFF)),
        listOf(Color(0xFFFFC9E9), Color(0xFFFFFFFF)),
        listOf(Color(0xFFE84A5F), Color(0xFFFFFFFF))
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .align(alignment = Alignment.CenterHorizontally))
                items.forEach { (item,label) ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(label) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                            when(item) {
                                Icons.Default.Settings -> Settings()
                                Icons.Default.Logout -> authViewModel.authService.logout()
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Icon Menu")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Menú Principal",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                ), fontSize = 18.sp
            )
        }

        LazyColumn(
            Modifier.fillMaxSize()
                .padding(top = 100.dp)
        ) {
            items(4) { index ->
                var isActive by remember { mutableStateOf(false) }
                val offset by animateDpAsState(
                    if (isActive) (-index * 30).dp else (-index * 10).dp,
                    animationSpec = tween(durationMillis = 300)
                )

                val route = listOf("","clothesEntryScreen","","inventoryOptionsScreen")
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Card(
                        onClick = {
                            isActive = !isActive
                            navController.navigate(route[index])
                                  },
                        modifier = Modifier
                            .size(width = 340.dp, height = 180.dp)
                            .offset(y = offset)
                            .background(Color.Transparent)
                    ) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(brush = Brush.verticalGradient(colors[index]))
                        ) {
                            Text(
                                text = names[index],
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
    }
}




fun Settings() {

}




