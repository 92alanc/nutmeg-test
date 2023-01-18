package com.alancamargo.nutmegtest.albums.ui

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.alancamargo.nutmegtest.albums.ui.robot.onActivityLaunched
import com.alancamargo.nutmegtest.core.design.dialogue.DialogueHelper
import com.alancamargo.nutmegtest.core.test.mockWebResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltAndroidTest
class AlbumListActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(AlbumListActivity::class.java)

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

    @Test
    fun whenServerReturnsEmpty_shouldShowDialogue() {
        mockWebResponse(jsonAssetPath = "albums_empty.json")

        onActivityLaunched check {
            emptyStateDialogueIsVisible()
        }
    }

    @Test
    fun withServerError_shouldDisplayDialogue() {
        mockWebResponse(
            jsonAssetPath = "albums_empty.json",
            code = HttpURLConnection.HTTP_INTERNAL_ERROR
        )

        onActivityLaunched check {
            serverErrorDialogueIsVisible()
        }
    }

    @Test
    fun whenLoading_shouldDisplayShimmer() {
        onActivityLaunched check {
            shimmerIsVisible()
        }
    }

    @Test
    fun whenClickingOnAppInfoMenuItem_shouldShowDialogue() {
        onActivityLaunched perform {
            clickAppInfoMenuItem()
        } check {
            appInfoDialogueIsVisible()
        }
    }
}
