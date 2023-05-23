package org.minutodedios.roperos.services.database

import org.minutodedios.roperos.model.Category
import org.minutodedios.roperos.model.Sale


/**
 * Interfaz o servicio de la base de datos
 */
sealed interface IDatabaseService {
    suspend fun inventoryForLocation(locationId: String): List<Category>
    suspend fun updateSubcategoryQuantity(categoryId: String, locationId: String, subcategoryId: String, newQuantity: Int)
    suspend fun makeSale(sale: Sale)
}