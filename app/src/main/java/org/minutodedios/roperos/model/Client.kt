package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable

/**
 * Información de un cliente del banco de ropa
 */
@Serializable
data class Client(
    val name: String,
    val lastname: String,
    val phone: String?,
    val idType: String,
    val id: String
)