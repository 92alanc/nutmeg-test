package com.alancamargo.nutmegtest.albums.ui.robot

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.alancamargo.nutmegtest.albums.ui.AlbumListActivity
import com.alancamargo.nutmegtest.albums.ui.AlbumListActivityTest

fun AlbumListActivityTest.onActivityLaunched(
    block: AlbumListActivityRobot.() -> Unit
): AlbumListActivityRobot {
    return AlbumListActivityRobot(activityScenarioRule).apply(block)
}

val AlbumListActivityTest.onActivityLaunched: AlbumListActivityRobot
    get() = onActivityLaunched {}

class AlbumListActivityRobot(private val rule: ActivityScenarioRule<AlbumListActivity>) {

    infix fun perform(block: AlbumListActionRobot.() -> Unit): AlbumListActionRobot {
        return AlbumListActionRobot(rule).apply(block)
    }

    infix fun check(block: AlbumListAssertionRobot.() -> Unit) {
        AlbumListAssertionRobot(rule).run(block)
    }
}
