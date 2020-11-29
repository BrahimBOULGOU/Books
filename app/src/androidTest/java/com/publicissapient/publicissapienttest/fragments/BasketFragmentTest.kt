package com.publicissapient.publicissapienttest.fragments

import CustomAssertions.Companion.hasItemCount
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.ui.viewholders.ListBookViewHolder
import com.publicissapient.publicissapienttest.ui.activitys.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BasketFragmentTest {

    @Before
    fun setup() {
       val activityScenario = ActivityScenario.launch(MainActivity::class.java)

    }

    @Test
    fun testOnlyBayedBooksAreDisplayed() {
        Espresso.onView(withId(R.id.booksLayout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.booksRecycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListBookViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Espresso.onView(withId(R.id.layoutdetails))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.addToBasket)).perform(
            ViewActions.click()
        )

        Espresso.onView(withId(R.id.booksLayout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.toolbar)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.imageView3)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.layoutBasket))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.soldBooksRecyclerView)).check(hasItemCount(1))

    }

}