package org.minutodedios.roperos.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.minutodedios.roperos.R
import org.minutodedios.roperos.ui.navigation.main.MainNavigationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryOptionsScreen(navController: NavHostController) {
    data class CardStructure(
        val tittle: String,
        val image: Int
    )

    val options = listOf(
        CardStructure("Hombre", R.drawable.logo),
        CardStructure("Mujer", R.drawable.logo),
        CardStructure("Niños", R.drawable.logo),
        CardStructure("Niñas", R.drawable.logo),
        CardStructure("Bebés", R.drawable.logo),
        CardStructure("Otros", R.drawable.logo)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        IconButton(onClick = {navController.navigate(MainNavigationRoutes.HomeScreenRoute.route)}) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Icon Menu")
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
            text = "Inventario",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
            ), fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(options) { item ->
                    Card(
                        onClick = {navController.navigate("inventoryScreen")},
                        modifier = Modifier
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = item.image),
                                contentDescription = "Image for Option",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                            )

                            Text(
                                text = item.tittle,
                                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 20.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(60.dp))
    }



}