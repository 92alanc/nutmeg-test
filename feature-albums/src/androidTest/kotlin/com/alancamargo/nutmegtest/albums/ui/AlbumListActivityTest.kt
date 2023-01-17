package com.alancamargo.nutmegtest.albums.ui

import com.alancamargo.nutmegtest.albums.ui.robot.onActivityLaunched
import com.alancamargo.nutmegtest.core.design.dialogue.DialogueHelper
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class AlbumListActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockDialogueHelper: DialogueHelper

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun a() {
        onActivityLaunched {

        }
    }
}
