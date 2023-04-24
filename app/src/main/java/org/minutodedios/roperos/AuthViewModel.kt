package org.minutodedios.roperos

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import org.minutodedios.roperos.services.authentication.IAuthUser
import org.minutodedios.roperos.services.authentication.IAuthenticationService
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val authService: IAuthenticationService
) : ViewModel() {

    var authenticated: Boolean by mutableStateOf(false)
        private set

    var currentUser: IAuthUser? by mutableStateOf(null)
        private set

    init {
        authService.authStateListener { isAuthenticated, user ->
            authenticated = isAuthenticated
            currentUser = user
        }
    }
}