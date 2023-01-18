package com.alancamargo.nutmegtest.albums.ui

import com.alancamargo.nutmegtest.core.di.BaseUrl
import com.alancamargo.nutmegtest.core.di.BaseUrlModule
import com.alancamargo.nutmegtest.core.test.mockWebServer
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [BaseUrlModule::class]
)
object TestNetworkModule {

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): HttpUrl = runBlocking(Dispatchers.IO) { mockWebServer.url("/") }
}
