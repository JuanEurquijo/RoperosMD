package org.minutodedios.roperos.model

import kotlinx.serialization.Serializable
import org.minutodedios.roperos.model.serializer.BigDecimalSerializer
import java.math.BigDecimal

/**
 * Para saber cuánto había que pagar, con cuánto pagó y cuánto se le tiene que devolver
 */
@Serializable
data class Contribution(
    @Serializable(with = BigDecimalSerializer::class) val total: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class) val payWith: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class) val change: BigDecimal
)
