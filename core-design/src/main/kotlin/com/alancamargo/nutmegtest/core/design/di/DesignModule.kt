package com.alancamargo.nutmegtest.core.design.di

import com.alancamargo.nutmegtest.core.design.dialogue.DialogueHelper
import com.alancamargo.nutmegtest.core.design.dialogue.DialogueHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
internal abstract class DesignModule {

    @Binds
    @ActivityScoped
    abstract fun bindDialogueHelper(impl: DialogueHelperImpl): DialogueHelper
}
