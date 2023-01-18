package com.alancamargo.nutmegtest.albums.ui

import com.alancamargo.nutmegtest.core.design.di.DesignModule
import com.alancamargo.nutmegtest.core.design.dialogue.DialogueHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DesignModule::class]
)
object TestDesignModule {

    @Provides
    @Singleton
    fun provideDialogueHelper(): DialogueHelper = mockk(relaxed = true)
}
