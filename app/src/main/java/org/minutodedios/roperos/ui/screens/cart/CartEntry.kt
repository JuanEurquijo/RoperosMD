package org.minutodedios.roperos.ui.screens.cart

import org.minutodedios.roperos.model.Subcategory
import java.math.BigDecimal

data class CartEntry(
    val category: String,
    val subcategory: Subcategory,
    val quantity: Int,
    val discount: BigDecimal = BigDecimal.ZERO,
) {
    val price: BigDecimal
        get() = subcategory.price * quantity.toBigDecimal() * (BigDecimal.ONE - discount);
}
