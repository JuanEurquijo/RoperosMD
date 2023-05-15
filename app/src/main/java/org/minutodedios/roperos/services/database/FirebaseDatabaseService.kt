package org.minutodedios.roperos.services.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import org.minutodedios.roperos.model.Category
import org.minutodedios.roperos.model.Subcategory

/**
 * Proveedor de base de datos de Firebase
 */
class FirebaseDatabaseService(
    /**
     * Proveedor de base de datos de Firestore
     */
    private val database: FirebaseFirestore = Firebase.firestore,

    /**
     * Categorias de la base de datos
     */
) : IDatabaseService {

    override suspend fun inventoryForLocation(locationId: String): List<Category> =
        database.collection("inventory").get().await().documents.map { category ->
            // For each subcategory (as Map) inside the categories
            val subcategories =
                (category.get("subcategories") as Map<String, Any>)
                    // Camisas, Chaquetas, Etc...
                    .filter { subcategory ->
                        // Inventory and Price
                        val subcategoryMap = subcategory.value as Map<String, Any>

                        // Check if it contains 'inventory array' and 'quantity'
                        if (subcategoryMap.containsKey("inventory") && subcategoryMap.containsKey("price")) {

                            // Parse inventory as an array
                            val inventory = subcategoryMap["inventory"] as Map<String, Any>

                            // Check if current subcategory has location
                            if (inventory.containsKey(locationId) && (inventory[locationId] as Number).toInt() > 0) {
                                // Subcategory is contained
                                return@filter true
                            }
                        }

                        // Subcategory was not found
                        return@filter false
                    }
                    // Camisas, Chaquetas, Etc ...
                    .map { subcategory ->
                        val content = subcategory.value as Map<String, Any>
                        Subcategory(
                            subcategory.key,
                            (content["price"] as Number).toDouble(),
                            (content["inventory"] as Map<String, Int>)[locationId]!!
                        )
                    }

            // Append the category
            Category(
                category.id,
                subcategories
            )
        }
}