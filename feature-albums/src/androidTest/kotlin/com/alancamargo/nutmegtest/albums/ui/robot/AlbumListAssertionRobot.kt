package com.alancamargo.nutmegtest.albums.ui.robot

import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import com.alancamargo.nutmegtest.albums.R
import com.alancamargo.nutmegtest.core.design.dialogue.DialogueHelper
import com.alancamargo.nutmegtest.core.test.onViewWithId
import com.alancamargo.nutmegtest.core.test.withRecyclerViewItemCount
import io.mockk.verify
import org.hamcrest.Description
import org.hamcrest.Matcher
import com.alancamargo.nutmegtest.core.design.R as R2

class AlbumListAssertionRobot {

    fun recyclerViewItemCountIs(count: Int) {
        onViewWithId(R.id.recyclerView).check(withRecyclerViewItemCount(count))
    }

    fun shimmerIsVisible() {
        onViewWithId(R.id.shimmerContainer).check(matches(withVisibleChild(R.id.shimmerRoot)))
    }

    fun appInfoDialogueIsVisible(mockDialogueHelper: DialogueHelper) {
        verify {
            mockDialogueHelper.showDialogue(
                context = any(),
                iconRes = R2.mipmap.ic_launcher_round,
                titleRes = R2.string.app_name,
                messageRes = R.string.app_info,
                buttonTextRes = R2.string.ok
            )
        }
    }

    private fun withVisibleChild(@IdRes childId: Int): Matcher<View> {
        return object : BoundedMatcher<View, FrameLayout>(FrameLayout::class.java) {
            override fun matchesSafely(item: FrameLayout?): Boolean {
                val child = item?.getChildAt(0)

                return child?.id == childId && child.isVisible
            }

            override fun describeTo(description: Description?) {
                description?.appendText("with child ID: ")
            }
        }
    }
}
