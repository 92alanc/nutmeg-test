package com.alancamargo.nutmegtest.albums.ui.robot

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.alancamargo.nutmegtest.albums.R
import com.alancamargo.nutmegtest.core.test.onViewWithId
import com.alancamargo.nutmegtest.core.test.withRecyclerViewItemCount

class AlbumListAssertionRobot {

    fun recyclerViewItemCountIs(count: Int) {
        onViewWithId(R.id.recyclerView).check(withRecyclerViewItemCount(count))
    }

    fun shimmerIsVisible() {
        onViewWithId(R.id.shimmerContainer).check(matches(isDisplayed()))
    }
}
