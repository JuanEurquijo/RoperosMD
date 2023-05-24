package org.minutodedios.roperos.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.minutodedios.roperos.services.database.FirebaseDatabaseService
import org.minutodedios.roperos.services.database.IDatabaseService

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    fun provideDatabaseService(): IDatabaseService {
        return FirebaseDatabaseService()
    }
}