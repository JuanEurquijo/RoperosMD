package org.minutodedios.roperos.services.authentication.firebase

import com.google.firebase.auth.FirebaseUser
import org.minutodedios.roperos.services.authentication.IAuthUser

/**
 * Proveedor del usuario autenticado de Firebase
 */
class FirebaseAuthUser(
    private val firebaseUser: FirebaseUser
) : IAuthUser {
    override val email: String get() = firebaseUser.email!!
}