package org.minutodedios.roperos.screens.login
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
     private val auth: FirebaseAuth = Firebase.auth
     private val _loading = MutableLiveData(false)

    fun signIn(
        email: String,
        password: String,
        context: Context,
        home: () -> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener { task ->
                       Log.d("RoperosMD","Sign in successfully!")
                        home()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context,e.message, Toast.LENGTH_SHORT).show()
                }
        }catch (ex:Exception){
            Log.d("RoperosMD","Error: ${ex.message}")
        }
    }

    fun signUp(
        email:String,
        password: String,
        context: Context,
        home: () -> Unit
    ){
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener { task->
                    home()
                    _loading.value = false
                    val name = task.user?.email?.split("@")?.get(0)
                    createUser(name)
                }
                .addOnFailureListener { e->
                    Toast.makeText(context,e.message, Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun createUser(name: String?) {
      val userId = auth.currentUser?.uid
        val user = mutableMapOf<String,Any>()

        user["user_id"] = userId.toString()
        user["name"] = name.toString()
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("RoperosMD","Create successfully ${it.id}")
            }.addOnFailureListener {
                Log.d("RoperosMD","Error ${it}")
            }
    }
}