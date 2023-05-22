package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable

/**
 * Registro de una venta
 */
@Serializable
data class Sale(
    val id: String,
    val client: Client?,
    val products: List<Product>,
    val location: String,
    val contribution: Contribution,
    val unixTime: Int
) {

    /**
     * Auto-generated fields based con Location
     */
    constructor(
        client: Client?,
        products: List<Product>,
        location: Location,
        contribution: Contribution,
        unixTime: Int
    ) : this(
        "${location.id.uppercase()}_${location.consecutive}",
        client,
        products,
        location.id,
        contribution,
        unixTime
    )
}
