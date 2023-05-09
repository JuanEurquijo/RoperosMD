package org.minutodedios.roperos.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.minutodedios.roperos.R
import org.minutodedios.roperos.ui.navigation.main.MainNavigationRoutes

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesEntryScreen(
    navController: NavHostController
) {
    data class ListItemData(
        val headlineText: String,
        var value: MutableState<Int>

    )

    val listItems = mutableStateListOf(
        ListItemData("Camisas - Camisetas", mutableStateOf(1)),
        ListItemData("Chaquetas", mutableStateOf(1)),
        ListItemData("Pantalones - Jeans", mutableStateOf(1)),
        ListItemData("Pantalonetas - Bermudas", mutableStateOf(1)),
        ListItemData("Pijamas", mutableStateOf(1)),
        ListItemData("Trajes", mutableStateOf(1)),
        ListItemData("Zapatos", mutableStateOf(1)),
        ListItemData("Otros", mutableStateOf(1)),
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
            text = "Ingreso de Prendas",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
            ), fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            content = {
                Text(
                    text = "Tipo de Prenda",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                    ), fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(110.dp))
                Text(
                    text = "Cantidad",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                    ), fontSize = 16.sp
                )
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                Modifier.fillMaxHeight()
            ) {
                itemsIndexed(listItems) { index, item ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent),
                        headlineText = { Text(
                            text=item.headlineText,
                            style = TextStyle(
                                fontWeight = FontWeight.Light,
                            ), fontSize = 15.sp
                        )
                        },
                        trailingContent = {Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { item.value.value -= 1},
                                modifier = Modifier.size(15.dp)) {
                                Icon(imageVector = Icons.Filled.Remove, contentDescription = "Icon Remove")
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = item.value.value.toString(),
                                fontSize = 15.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            IconButton(
                                onClick = { item.value.value += 1 },
                                modifier = Modifier.size(15.dp)) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Icon Add")
                            }
                        }},
                        leadingContent = {
                            Row{
                                Image(
                                    painter = painterResource(R.drawable.ic_launcher_background),
                                    contentDescription = "Rounded Image",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /* acción del botón */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrar Prendas")
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}