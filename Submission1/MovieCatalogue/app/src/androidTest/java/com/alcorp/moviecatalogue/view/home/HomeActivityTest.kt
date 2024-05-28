package com.alcorp.moviecatalogue.view.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.alcorp.moviecatalogue.R
import com.alcorp.moviecatalogue.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val movieData = DataDummy.generateDummyMovie()
    private val tvShowData = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.rec_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rec_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieData.size))
    }

    @Test
    fun loadTvShow() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rec_tvshow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rec_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShowData.size))
    }

    @Test
    fun loadDetail(){
        onView(withId(R.id.rec_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tvTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvTitle)).check(ViewAssertions.matches(withText(movieData[0].title)))
        onView(withId(R.id.tvYear)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvYear)).check(ViewAssertions.matches(withText(movieData[0].yearRelease)))
        onView(withId(R.id.tvOview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvOview)).check(ViewAssertions.matches(withText(movieData[0].synopsis)))
    }
}