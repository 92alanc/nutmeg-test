package com.alancamargo.nutmegtest.albums.ui.robot

import androidx.test.core.app.ActivityScenario
import com.alancamargo.nutmegtest.albums.ui.AlbumListActivity
import com.alancamargo.nutmegtest.core.test.mockWebResponse

fun onActivityLaunched(block: AlbumListActivityRobot.() -> Unit): AlbumListActivityRobot {
    ActivityScenario.launch(AlbumListActivity::class.java)
    return AlbumListActivityRobot().apply(block)
}

class AlbumListActivityRobot {

    fun mockSuccessfulAlbumsResponse() {
        mockWebResponse(jsonAssetPath = "albums_success.json")
    }

    fun mockSuccessfulUsersResponse() {
        mockWebResponse(jsonAssetPath = "users_success.json")
    }

    fun mockSuccessfulPhotosResponse() {
        mockWebResponse(jsonAssetPath = "photos_success.json")
    }

    infix fun perform(block: AlbumListActionRobot.() -> Unit): AlbumListActionRobot {
        return AlbumListActionRobot().apply(block)
    }
}
