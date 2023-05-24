package org.minutodedios.roperos.services.database

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.minutodedios.roperos.model.Category
import org.minutodedios.roperos.model.Sale
import org.minutodedios.roperos.model.Subcategory
import java.math.BigDecimal

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
                            BigDecimal((content["price"] as Number).toString()),
                            (content["inventory"] as Map<String, Int>)[locationId]!!
                        )
                    }

            // Append the category
            Category(
                category.id,
                subcategories
            )
        }

    override suspend fun updateSubcategoryQuantity(
        categoryId: String,
        locationId: String,
        subcategoryId: String,
        newQuantity: Int
    ) {
        val categoryRef = database.collection("inventory").document(categoryId)
        val categorySnapshot = categoryRef.get().await()

        if (categorySnapshot.exists()) {
            val subcategoriesMap = categorySnapshot["subcategories"] as? Map<String, Any>
            val subcategoryMap = subcategoriesMap?.get(subcategoryId) as? Map<String, Any>

            if (subcategoryMap != null) {
                val inventoryMap = subcategoryMap["inventory"] as? Map<String, Any>

                if (inventoryMap != null && inventoryMap.containsKey(locationId)) {
                    val updatedInventory = inventoryMap.toMutableMap()
                    updatedInventory[locationId] = newQuantity

                    val updatedSubcategoryMap = subcategoryMap.toMutableMap()
                    updatedSubcategoryMap["inventory"] = updatedInventory

                    val updatedSubcategoriesMap = subcategoriesMap.toMutableMap()
                    updatedSubcategoriesMap[subcategoryId] = updatedSubcategoryMap

                    categoryRef.update("subcategories", updatedSubcategoriesMap)
                        .addOnSuccessListener {
                            Log.d("SUCCESS", "Actualización realizada correctamente")
                        }
                        .addOnFailureListener { e ->
                            Log.d("ERROR", "Ocurrió un error en la actualizaciòn")
                        }
                }
            }
        }
    }

    override suspend fun makeSale(sale: Sale) {
        val json = Json.encodeToString(sale)
        val serializedSale = Gson().fromJson<Map<String, Any>>(json, object : TypeToken<Map<String?, Any?>?>() {}.type)
        database.collection("sales").add(serializedSale).await()
    }


}