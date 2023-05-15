package org.minutodedios.roperos.services.database

import org.minutodedios.roperos.model.Category


/**
 * Interfaz o servicio de la base de datos
 */
sealed interface IDatabaseService {
    suspend fun inventoryForLocation(locationId: String): List<Category>
}