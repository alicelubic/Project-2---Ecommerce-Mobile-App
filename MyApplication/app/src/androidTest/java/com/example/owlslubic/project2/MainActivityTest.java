package com.example.owlslubic.project2;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by owlslubic on 7/24/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    //none of these work yet because I have to figure out how to access the specific views in the recyclerview
    //but I figured it was worth starting this part


    @Test
    public void testViewsDisplayed() throws Exception {
        onView(withId(R.id.cardview_main_activity)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_main)).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar_main)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddToCart() throws Exception {
        onView(withId(R.id.cardview_main_activity))
                .perform(click());
        onView(withId(R.id.fab_detail_dialog))
                .perform(click());
        onView(withId(R.id.cardview_cart)).check(matches(isDisplayed()));

    }

    @Test
    public void testSearch() throws Exception {
        String query = "grass";
        String expectedAnswer ="Bamboo";
        onView(withId(R.id.search))
                .perform(click(),typeText(query),pressImeActionButton());
        onView(withId(android.R.id.text1)).check(matches(withText(expectedAnswer)));
        onView(withId(R.id.search))
                .perform(click(),typeText("Grass"),pressImeActionButton());
        onView(withId(android.R.id.text1)).check(matches(withText(expectedAnswer)));
        onView(withId(R.id.search))
                .perform(click(),typeText("ivy"),pressImeActionButton());
        onView(withId(android.R.id.text1)).check(matches(withText("English Ivy")));


    }
}
