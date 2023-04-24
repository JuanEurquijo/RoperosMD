package org.minutodedios.roperos.services.authentication.mock

import org.minutodedios.roperos.services.authentication.IAuthUser
import org.minutodedios.roperos.services.authentication.IAuthenticationService

/**
 * Servicio de autenticación falseado para pruebas
 */
class MockAuthenticationService() : IAuthenticationService {

    private var currentUser: MockAuthUser? = null

    override fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        throw UnsupportedOperationException("Mock authentication service is not compatible")
    }

    override fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        currentUser = MockAuthUser(email)
    }

    override fun logout() {
        currentUser = null
    }

    override fun authStateListener(listener: (Boolean, IAuthUser?) -> Unit): Any {
        return listener.invoke(authenticated, currentUser)
    }

    override fun removeStateListener(listener: Any) {
        throw UnsupportedOperationException("Mock authentication service is not compatible")
    }

    override val authenticated: Boolean
        get() = currentUser != null

    override val user: IAuthUser?
        get() = currentUser
}