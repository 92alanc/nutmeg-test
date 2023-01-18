package com.alancamargo.nutmegtest.albums.ui

import com.alancamargo.nutmegtest.albums.ui.robot.onActivityLaunched
import com.alancamargo.nutmegtest.core.design.dialogue.DialogueHelper
import com.alancamargo.nutmegtest.core.test.mockWebResponse
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
    fun whenServerReturnsSuccess_recyclerViewShouldDisplayCorrectNumberOfItems() {
        mockWebResponse(jsonAssetPath = "albums_success.json")
        mockWebResponse(jsonAssetPath = "users_success.json")
        mockWebResponse(jsonAssetPath = "photos_success.json")

        onActivityLaunched check {
            recyclerViewItemCountIs(count = 1)
        }
    }
}
