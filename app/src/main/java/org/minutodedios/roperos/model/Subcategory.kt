package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable

@Serializable
data class Subcategory(
    val subcategory: String,
    val price: Double,
    val quantity: Int
)
