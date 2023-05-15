package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val category: String,
    val subcategories: List<Subcategory>
)