package org.minutodedios.roperos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.minutodedios.roperos.services.authentication.IAuthUser
import org.minutodedios.roperos.services.authentication.IAuthenticationService
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val authService: IAuthenticationService
) : ViewModel() {

    var authenticated: Boolean by mutableStateOf(authService.authenticated)
        private set

    var currentUser: IAuthUser? by mutableStateOf(authService.user)
        private set

    init {
        authService.authStateListener { isAuthenticated, user ->
            authenticated = isAuthenticated
            currentUser = user
        }
    }
}