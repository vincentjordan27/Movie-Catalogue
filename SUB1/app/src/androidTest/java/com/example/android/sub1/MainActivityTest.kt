package com.example.android.sub1

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.android.sub1.ui.MainActivity
import com.example.android.sub1.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun testMain(){
        onView(withId(R.id.rv_movie_frag)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_frag))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, scrollTo()),
                click())
        onView(withId(R.id.btn_fav)).perform(click())
        onView(withContentDescription(R.string.back_main)).perform(click())
        onView(withId(R.id.tvshow)).perform(click())

        onView(withId(R.id.rv_tv_show_frag)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show_frag))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.rv_fav_movie))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, scrollTo()),
                click())
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()
        onView(withId(R.id.swp_fav_movie)).perform(swipeDown())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_fav_tv_show))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, scrollTo()),
                click())
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()
        onView(withId(R.id.swp_fav_tv_show)).perform(swipeDown())



    }
}