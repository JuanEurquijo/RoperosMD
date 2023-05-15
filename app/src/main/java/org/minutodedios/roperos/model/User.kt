package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val fullname: String,
    val location: Location
)