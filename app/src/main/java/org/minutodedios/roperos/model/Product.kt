package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable
import org.minutodedios.roperos.model.serializer.BigDecimalSerializer
import org.minutodedios.roperos.ui.screens.cart.CartEntry
import java.math.BigDecimal

@Serializable
data class Product(
    val category: String,
    val subcategory: String,
    @Serializable(with = BigDecimalSerializer::class) val unitaryPrice: BigDecimal,
    val ammount: Int,
    @Serializable(with = BigDecimalSerializer::class) val discount: BigDecimal,
) {
    val price: BigDecimal
        get() = unitaryPrice * ammount.toBigDecimal() * (BigDecimal.ONE - discount);

    constructor(cartEntry: CartEntry) : this(
        cartEntry.category,
        cartEntry.subcategory.subcategory,
        cartEntry.subcategory.price,
        cartEntry.quantity,
        cartEntry.discount,
    );
}
