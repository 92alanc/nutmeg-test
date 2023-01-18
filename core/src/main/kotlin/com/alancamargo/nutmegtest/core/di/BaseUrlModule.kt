package com.alancamargo.nutmegtest.core.di

import androidx.annotation.VisibleForTesting
import com.alancamargo.nutmegtest.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
object BaseUrlModule {

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): HttpUrl = BuildConfig.BASE_URL.toHttpUrl()
}
