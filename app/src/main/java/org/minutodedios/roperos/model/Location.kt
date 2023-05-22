package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable

/**
 * Información sobre cada una de las ubicaciones (roperos)
 */
@Serializable
data class Location(
    val id: String,
    val city: String,
    val name: String,
    val consecutive: Int,
)