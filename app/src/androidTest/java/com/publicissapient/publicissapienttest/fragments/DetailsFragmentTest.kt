package com.publicissapient.publicissapienttest.fragments

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.ui.viewholders.ListBookViewHolder
import com.publicissapient.publicissapienttest.ui.activitys.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailsFragmentTest {

    @Before
    fun setup(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

    }

    @Test
     fun testAddToBasketButton() {
        onView(ViewMatchers.withId(R.id.booksLayout)).check(matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        onView(ViewMatchers.withId(R.id.booksRecycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListBookViewHolder>(
                0,
                ViewActions.click()
            )
        )
      onView(ViewMatchers.withId(R.id.layoutdetails))
            .check(matches(ViewMatchers.isDisplayed()))
      onView(ViewMatchers.withId(R.id.addToBasket)).check(matches(withText("Ajouter au panier"))).perform(ViewActions.click())
      onView(ViewMatchers.withId(R.id.booksLayout)).check(matches(ViewMatchers.isDisplayed()))

      onView(ViewMatchers.withId(R.id.booksRecycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListBookViewHolder>(
                0,
                ViewActions.click()
            )
      )
      onView(ViewMatchers.withId(R.id.layoutdetails))
            .check(matches(ViewMatchers.isDisplayed()))
      onView(ViewMatchers.withId(R.id.addToBasket)).check(matches(withText("Supprimer du panier")))
    }

}