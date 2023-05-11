package org.minutodedios.roperos

import org.minutodedios.roperos.model.Item

data class ItemListState(
    val isLoading: Boolean = false,
    val items: List<Item> = emptyList(),
    val error: String = ""
)
