package com.alancamargo.nutmegtest.core.di

import android.content.Context
import com.alancamargo.nutmegtest.core.database.LocalDatabaseProvider
import com.alancamargo.nutmegtest.core.database.LocalDatabaseProviderImpl
import com.alancamargo.nutmegtest.core.log.Logger
import com.alancamargo.nutmegtest.core.log.LoggerImpl
import com.alancamargo.nutmegtest.core.network.ApiProvider
import com.alancamargo.nutmegtest.core.network.ApiProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoreModule {

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideApiProvider(impl: ApiProviderImpl): ApiProvider = impl

    @Provides
    @Singleton
    fun provideLocalDatabaseProvider(
        @ApplicationContext context: Context
    ): LocalDatabaseProvider = LocalDatabaseProviderImpl(context)

    @Provides
    @Singleton
    fun provideLogger(impl: LoggerImpl): Logger = impl
}
