package org.minutodedios.roperos.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.minutodedios.roperos.services.authentication.IAuthenticationService
import org.minutodedios.roperos.services.authentication.firebase.FirebaseAuthenticationService

/**
 * Módulo de inyección de la dependencia de autenticación
 */
@Module
@InstallIn(SingletonComponent::class)
internal object AuthenticationModule {
    @Provides
    fun provideAuthenticationService(): IAuthenticationService {
        return FirebaseAuthenticationService()
    }
}