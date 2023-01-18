package com.alancamargo.nutmegtest.albums.ui.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alancamargo.nutmegtest.albums.R
import com.alancamargo.nutmegtest.core.test.withRecyclerViewItemCount

class AlbumListAssertionRobot {

    fun recyclerViewItemCountIs(count: Int) {
        onView(withId(R.id.recyclerView)).check(withRecyclerViewItemCount(count))
    }
}
