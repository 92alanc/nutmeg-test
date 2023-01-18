package com.alancamargo.nutmegtest.albums.ui.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.alancamargo.nutmegtest.albums.R
import com.alancamargo.nutmegtest.albums.ui.AlbumListActivity

class AlbumListActionRobot(private val rule: ActivityScenarioRule<AlbumListActivity>) {

    fun clickAppInfoMenuItem() {
        openActionBarOverflowOrOptionsMenu(
            InstrumentationRegistry.getInstrumentation().targetContext
        )
        onView(withText(R.string.about)).perform(click())
    }

    infix fun check(block: AlbumListAssertionRobot.() -> Unit) {
        AlbumListAssertionRobot(rule).run(block)
    }
}
