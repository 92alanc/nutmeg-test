package com.alancamargo.nutmegtest.albums.ui.robot

fun onActivityLaunched(block: AlbumListActivityRobot.() -> Unit): AlbumListActivityRobot {
    return AlbumListActivityRobot().apply(block)
}

val onActivityLaunched = onActivityLaunched {}

class AlbumListActivityRobot {

    infix fun perform(block: AlbumListActionRobot.() -> Unit): AlbumListActionRobot {
        return AlbumListActionRobot().apply(block)
    }

    infix fun check(block: AlbumListAssertionRobot.() -> Unit) {
        AlbumListAssertionRobot().run(block)
    }
}
