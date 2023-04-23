package org.minutodedios.roperos.services.authentication.firebase

import com.google.firebase.auth.FirebaseUser
import org.minutodedios.roperos.services.authentication.IAuthUser

class FirebaseAuthUser(
    private val firebaseUser: FirebaseUser
) : IAuthUser {
    val email: String get() = firebaseUser.email!!
}