package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val id: String,
    val city: String,
    val name: String,
)