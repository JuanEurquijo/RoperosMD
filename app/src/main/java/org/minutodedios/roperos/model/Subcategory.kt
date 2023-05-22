package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable
import org.minutodedios.roperos.model.serializer.BigDecimalSerializer
import java.math.BigDecimal

@Serializable
data class Subcategory(
    val subcategory: String,
    @Serializable(with = BigDecimalSerializer::class) val price: BigDecimal,
    val quantity: Int
)
