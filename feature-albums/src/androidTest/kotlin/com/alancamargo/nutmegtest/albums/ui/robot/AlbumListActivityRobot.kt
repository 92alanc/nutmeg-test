package com.alancamargo.nutmegtest.albums.ui.robot

import androidx.test.core.app.ActivityScenario
import com.alancamargo.nutmegtest.albums.ui.AlbumListActivity

fun onActivityLaunched(block: AlbumListActivityRobot.() -> Unit): AlbumListActivityRobot {
    ActivityScenario.launch(AlbumListActivity::class.java)
    return AlbumListActivityRobot().apply(block)
}

class AlbumListActivityRobot {

    infix fun perform(block: AlbumListActionRobot.() -> Unit): AlbumListActionRobot {
        return AlbumListActionRobot().apply(block)
    }
}
