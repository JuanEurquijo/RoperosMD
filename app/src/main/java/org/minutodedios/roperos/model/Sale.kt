package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Registro de una venta
 */
@Serializable
data class Sale(
    val id: String,
    val client: Client?,
    val issuedBy: User,
    val products: List<Product>,
    val location: String,
    val contribution: Contribution,
    val unixTime: Long
) {

    /**
     * Auto-generated fields based con Location
     */
    constructor(
        client: Client?,
        issuedBy: User,
        products: List<Product>,
        location: Location,
        contribution: Contribution,
        unixTime: Long
    ) : this(
        "${UUID.randomUUID()}",
        client,
        issuedBy,
        products,
        location.id,
        contribution,
        unixTime
    )
}
