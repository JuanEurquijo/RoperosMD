package org.minutodedios.roperos.screens.home

import org.minutodedios.roperos.R

sealed class ItemsBottomNav (
    val icon: Int,
    val title: String,
    val route: String
){
    object Screen1: ItemsBottomNav(R.drawable.icon_home,"Inicio","screen1")
    object Screen2: ItemsBottomNav(R.drawable.icon_cart,"Carrito","screen2")
    object Screen3: ItemsBottomNav(R.drawable.icon_menu,"Operaciones","screen3")
}
