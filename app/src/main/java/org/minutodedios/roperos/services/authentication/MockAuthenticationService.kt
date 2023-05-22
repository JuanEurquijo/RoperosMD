package org.minutodedios.roperos.services.authentication

import org.minutodedios.roperos.model.Location
import org.minutodedios.roperos.model.User

/**
 * Servicio de autenticación falseado para pruebas
 */
class MockAuthenticationService(
    val initAsAuthenticated: Boolean = false
) : IAuthenticationService {

    private var _currentUser: User? = null

    override val isReady: Boolean = true

    init {
        if (initAsAuthenticated) {
            _currentUser = User(
                "hello@word.com", "Lorem",
                Location("lorem_ipsum", "Ipsum", "Dolor", 0)
            )
        }
    }

    override fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        throw UnsupportedOperationException("Mock authentication service is not compatible with registration")
    }

    override fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        _currentUser = User(
            email, "Lorem",
            Location("lorem_ipsum", "Ipsum", "Dolor", 0)
        )
    }

    override fun logout() {
        _currentUser = null
    }

    override fun addAuthStateListener(listener: (Boolean) -> Unit) {
        listener.invoke(authenticated)
    }

    override val authenticated: Boolean
        get() = (_currentUser != null)

    override val user: User?
        get() = _currentUser
}