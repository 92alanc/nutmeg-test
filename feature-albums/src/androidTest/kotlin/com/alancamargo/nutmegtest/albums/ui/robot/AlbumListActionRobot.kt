package com.alancamargo.nutmegtest.albums.ui.robot

class AlbumListActionRobot {

    infix fun check(block: AlbumListAssertionRobot.() -> Unit) {
        AlbumListAssertionRobot().run(block)
    }
}
