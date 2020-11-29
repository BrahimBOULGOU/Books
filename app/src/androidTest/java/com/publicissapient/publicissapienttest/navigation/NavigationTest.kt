package com.publicissapient.publicissapienttest.navigation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.ui.viewholders.ListBookViewHolder
import com.publicissapient.publicissapienttest.ui.activitys.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NavigationTest {

    @Test
    fun testNavigation() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.booksLayout)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.toolbar)).perform(click())
        onView(withId(R.id.imageView3)).perform(click())
        onView(withId(R.id.layoutBasket)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.booksLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.booksRecycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListBookViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.layoutdetails)).check(matches(isDisplayed()))
    }
}