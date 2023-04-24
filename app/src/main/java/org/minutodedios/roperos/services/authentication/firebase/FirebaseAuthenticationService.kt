package org.minutodedios.roperos.services.authentication.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.minutodedios.roperos.services.authentication.IAuthUser
import org.minutodedios.roperos.services.authentication.IAuthenticationService

class FirebaseAuthenticationService(
    /**
     * Proveedor de autenticación de Firebase
     */
    private val auth: FirebaseAuth = Firebase.auth,

    /**
     * Usuario de firebase actual (Wrapper para interfaz IAuthUser), null si no se está autenticado
     */
    private var _currentUser: FirebaseAuthUser? = if (auth.currentUser != null) FirebaseAuthUser(
        auth.currentUser!!
    ) else null,
) : IAuthenticationService {

    init {
        // Listener en cambios de autenticación
        auth.addAuthStateListener {
            _currentUser = if (it.currentUser != null) FirebaseAuthUser(
                it.currentUser!!
            ) else null;
        }
    }

    override val authenticated: Boolean
        get() {
            // Meter el FirebaseUser dentro de FirebaseAuthUser
            _currentUser = if (auth.currentUser != null) FirebaseAuthUser(
                auth.currentUser!!
            ) else null
            // Retorna True si el usuario no es null
            return _currentUser != null;
        }


    override val user: IAuthUser get() = _currentUser as IAuthUser

    override fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            onResult.invoke(it.isSuccessful)
        }
    }

    override fun logout() {
        auth.signOut()
    }

    override fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            onResult.invoke(it.isSuccessful)
        }
    }

    override fun authStateListener(listener: (Boolean, IAuthUser?) -> Unit) {
        auth.addAuthStateListener {
            listener.invoke(
                it.currentUser != null,
                if (it.currentUser != null) FirebaseAuthUser(it.currentUser!!) else null
            );
        }
    }
}