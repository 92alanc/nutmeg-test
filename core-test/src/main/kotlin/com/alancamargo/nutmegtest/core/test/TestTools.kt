package com.alancamargo.nutmegtest.core.test

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

val mockWebServer = MockWebServer()

fun mockWebResponse(
    jsonAssetPath: String,
    code: Int = HttpURLConnection.HTTP_OK,
    delayResponse: Boolean = false
) {
    val json = getJsonFromAssets(jsonAssetPath)
    val response = MockResponse().setBody(json).setResponseCode(code).apply {
        if (delayResponse) {
            setBodyDelay(10, TimeUnit.SECONDS)
        }
    }
    mockWebServer.enqueue(response)
}

fun onViewWithId(@IdRes idRes: Int): ViewInteraction = onView(withId(idRes))

private fun getJsonFromAssets(path: String): String {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val assetManager = context.assets

    val inputStream = assetManager.open(path)
    return inputStream.bufferedReader().use { it.readText() }
}
