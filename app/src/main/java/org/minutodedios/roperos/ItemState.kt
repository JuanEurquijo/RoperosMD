package org.minutodedios.roperos

import org.minutodedios.roperos.model.Item

data class ItemState(
    val isLoading: Boolean = false,
    val item: Item? = null,
    val error: String = ""
)
