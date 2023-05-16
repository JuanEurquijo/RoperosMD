package org.minutodedios.roperos.services.authentication

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject
import org.minutodedios.roperos.model.User
import kotlin.properties.Delegates

/**
 * Proveedor del servicio de autenticación de Firebase
 */
class FirebaseAuthenticationService(
    /**
     * Proveedor de autenticación de Firebase
     */
    private val auth: FirebaseAuth = Firebase.auth,

    /**
     * Proveedor de base de datos de Firestore
     */
    private val database: FirebaseFirestore = Firebase.firestore,

    ) : IAuthenticationService {

    private var listeners = mutableListOf<(Boolean) -> Unit>()

    /**
     * Usuario actualmente autenticado
     */
    private var _currentUser by Delegates.observable<User?>(null) { _, _, newValue ->
        listeners.forEach { it.invoke(newValue != null) }
    }

    private var _isReady = false

    override val isReady: Boolean
        get() = _isReady

    private suspend fun findUserFromFirestore(firebaseUser: FirebaseUser): User {
        // Get the user email
        val userEmail = firebaseUser.email!!;

        // Get the user document
        val userDocument = database.collection("/users").whereEqualTo("email", userEmail).get()
            .await().documents.first()
            ?: throw IllegalArgumentException("User does not exist in registry")

        // Get the referenced location document
        val locationDocument = userDocument.getDocumentReference("location")?.get()?.await()
            ?: throw IllegalArgumentException("User location was not found")

        // Parse location document from JSON Format
        val locationJSON = (locationDocument.data?.let { JSONObject(it) }).toString()

        // Create from information
        return User(
            userEmail, userDocument.getString("fullname")!!, Json.decodeFromString(locationJSON)
        )
    }

    init {
        // Listener en cambios de autenticación
        auth.addAuthStateListener {
            runBlocking {
                _currentUser =
                    if (it.currentUser == null) null else findUserFromFirestore(it.currentUser!!)
            }

            Log.i(
                "FirebaseAuthenticationListener",
                "AuthenticationStatus[${this.authenticated}]: ${_currentUser.toString()}"
            )

            // Set is ready to true on every request
            _isReady = true
        }
    }

    override val authenticated: Boolean
        get() = _currentUser != null


    override val user: User?
        get() = _currentUser

    override fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            onResult.invoke(it.isSuccessful)
        }
    }

    override fun logout() {
        auth.signOut()
    }

    override fun addAuthStateListener(listener: (Boolean) -> Unit) {
        listeners.add(listener)
    }

    override fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            onResult.invoke(it.isSuccessful)
        }
    }
}