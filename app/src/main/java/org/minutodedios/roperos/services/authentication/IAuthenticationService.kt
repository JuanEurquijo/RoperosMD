package org.minutodedios.roperos.services.authentication

import org.minutodedios.roperos.model.User

/**
 * Interfaz o wrapper del servicio de autenticación
 */
sealed interface IAuthenticationService {
    /**
     * Registrar un nuevo usuario con su email y contraseña
     * @param onResult Callback de resultado, recibe como parámetro el resultado booleano
     */
    fun register(email: String, password: String, onResult: (Boolean) -> Unit)

    /**
     * Iniciar sesión con correo y contraseña
     * @param onResult Callback de resultado, recibe como parámetro el resultado booleano
     */
    fun login(email: String, password: String, onResult: (Boolean) -> Unit)

    /**
     * Cerrar la sesión actual
     */
    fun logout()

    /**
     * Agregar un callback listener para cuando cambie la autenticación
     * @param listener Recibe como parámetro el resultado booleano y el usuario
     * @return Objeto identificador del listener
     */
    fun addAuthStateListener(listener: (Boolean) -> Unit)

    /**
     * Getter para saber si se está autenticado
     */
    val authenticated: Boolean

    /**
     * Getter para el usuario, puede ser null si no está autenticado
     */
    val user: User?

    /**
     * Getter para saber si la autenticación está lista
     */
    val isReady: Boolean
}