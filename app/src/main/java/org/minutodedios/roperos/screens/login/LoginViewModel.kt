package org.minutodedios.roperos.screens.login
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
     private val auth: FirebaseAuth = Firebase.auth
     private val _loading = MutableLiveData(false)

    fun signIn(email:String, password:String, home: ()->Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                       Log.d("RoperosMD","Sign in successfully!")
                        home()
                    } else {
                        Log.d("RoperosMD","Something went wrong: ${task.result.toString()}")
                    }
                }
        } catch (ex:Exception){
            Log.d("RoperosMD","Error: ${ex.message}")
        }
    }
}