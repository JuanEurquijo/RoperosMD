package org.minutodedios.roperos.services.authentication

interface IAuthenticationService {
    /**
     * Registrar un nuevo usuario con su email y contraseña
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
     */
    fun authStateListener(listener: (Boolean, IAuthUser?) -> Unit)

    /**
     * Getter para saber si se está autenticado
     */
    val authenticated: Boolean

    /**
     * Getter para el usuario, puede ser null si no está autenticado
     */
    val user: IAuthUser
}