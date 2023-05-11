package org.minutodedios.roperos.repositories

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.CollectionReference
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import org.minutodedios.roperos.model.Item
import org.minutodedios.roperos.repositories.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class shirtRepository
@Inject
constructor(
    private val shirtList: CollectionReference
) {

    fun getShirtList(): Flow<Result<List<Item>>> = flow {
       try {
         emit(Result.Loading<List<Item>>())
           val gson = Gson()
           val itemList = shirtList.get().await().map { document ->
               gson.fromJson(gson.toJson(document.data), Item::class.java)
           }
           emit(Result.Success<List<Item>>(data = itemList))
       }catch (e: Exception){
           emit(Result.Error<List<Item>>(message = e.localizedMessage ?: "Error desconocido"))
       }
    }

    fun getShirtById(shirtId: String): Flow<Result<Item>> = flow {
        try {
            emit(Result.Loading<Item>())
            val shirt = shirtList.whereGreaterThanOrEqualTo("id",shirtId)
                .get()
                .await()
                .toObjects(Item::class.java)
                .first()
            emit(Result.Success<Item>(data = shirt))
        }catch (e: Exception){
            emit(Result.Error<Item>(message = e.localizedMessage ?: "Error desconocido"))
        }
    }

    fun updateShirt(shirtId:String,shirt:Item){
       try {
           val map = mapOf(
               "quantity" to shirt.quantity
           )
           shirtList.document(shirtId).update(map)
       }catch (e: Exception){
           e.printStackTrace()
       }
    }


}
