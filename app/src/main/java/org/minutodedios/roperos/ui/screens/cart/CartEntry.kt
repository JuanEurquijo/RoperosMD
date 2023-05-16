package org.minutodedios.roperos.ui.screens.cart

import org.minutodedios.roperos.model.Subcategory

data class CartEntry(
    val category: String,
    val subcategory: Subcategory,
    val quantity: Int
)
