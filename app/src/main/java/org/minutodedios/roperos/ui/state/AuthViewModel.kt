package org.minutodedios.roperos.ui.state

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.minutodedios.roperos.services.authentication.IAuthenticationService
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val authService: IAuthenticationService
) : ViewModel() {

    var authenticated: MutableLiveData<Boolean> = MutableLiveData(authService.authenticated)
        private set

    var user = MutableLiveData(authService.user)
        private set

    var isReady = MutableLiveData(authService.isReady)
        private set

    init {
        authService.addAuthStateListener {
            // Set new values
            authenticated.value = it
            user.value = authService.user
            isReady.value = authService.isReady

            Log.d(this::class.simpleName, "AuthenticationListenerInvoked")
        }
    }
}