package com.alancamargo.nutmegtest.albums.ui

import com.alancamargo.nutmegtest.albums.ui.robot.onActivityLaunched
import com.alancamargo.nutmegtest.core.design.dialogue.DialogueHelper
import com.alancamargo.nutmegtest.core.test.getJsonFromAssets
import com.alancamargo.nutmegtest.core.test.mockWebServer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.HttpURLConnection
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
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(getJsonFromAssets("albums_success.json"))
        )
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(getJsonFromAssets("users_success.json"))
        )
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(getJsonFromAssets("photos_success.json"))
        )

        onActivityLaunched {

        }
    }
}
