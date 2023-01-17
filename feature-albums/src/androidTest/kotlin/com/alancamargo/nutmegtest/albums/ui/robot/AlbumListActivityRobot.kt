package com.alancamargo.nutmegtest.albums.ui.robot

import androidx.test.core.app.ActivityScenario
import com.alancamargo.nutmegtest.albums.ui.AlbumListActivity

class AlbumListActivityRobot {

    fun launch() {
        ActivityScenario.launch(AlbumListActivity::class.java)
    }
}
