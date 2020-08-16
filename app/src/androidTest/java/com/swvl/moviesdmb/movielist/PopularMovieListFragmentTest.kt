package com.swvl.moviesdmb.movielist

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.movielist.PopularMovieListFragment
import com.swvl.moviesdmb.ui.movielist.PopularMovieListFragmentDirections
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


/**
 * Integration test for the Movie List screen.
 */
// TODO - Use FragmentScenario, see: https://github.com/android/android-test/issues/291
@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class PopularMovieListFragmentTest {

    @Test
    fun clickOneMovie_navigateToDetailFragmentOne() = runBlockingTest {
        // GIVEN - On the home screen
        val scenario =
            launchFragmentInContainer<PopularMovieListFragment>(Bundle(), R.style.AppTheme)
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
        // WHEN - Click on the first list item
        Espresso.onView(withId(R.id.movies_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )

        // THEN - Verify that we navigate to the detail screen
        Mockito.verify(navController).navigate(
            PopularMovieListFragmentDirections.actionPopularMovieListFragmentToMovieDetailFragment(
                Movie(
                    1,
                    "overview1",
                    "date1",
                    "poster1",
                    "path1",
                    "orgTitle1",
                    "title1", 0.1, "en"
                )
            )
        )
    }
}