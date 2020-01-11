package ru.aviasales.ui.activity


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.travelpayouts.travel.app.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val bottomBarTabView = onView(
            allOf(
                withId(R.id.bb_appinfo_tab),
                childAtPosition(
                    allOf(
                        withId(R.id.bottomBar),
                        childAtPosition(
                            withId(R.id.activityRootLayout),
                            1
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomBarTabView.perform(click())

        val profileMenuItemView = onView(
            allOf(
                withId(R.id.btnSettings),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    7
                )
            )
        )
        profileMenuItemView.perform(scrollTo(), click())

        val linearLayout = onView(
            allOf(
                withId(R.id.btnRegion),
                childAtPosition(
                    allOf(
                        withId(R.id.settingsGeneralContainer),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    3
                )
            )
        )
        linearLayout.perform(scrollTo(), click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.editTextMessage),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.searchView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Russia"), closeSoftKeyboard())

        val appCompatTextView = onView(
            allOf(
                withId(R.id.tvRegionName), withText("Russia"),
                childAtPosition(
                    allOf(
                        withId(R.id.rvItemsRecycler),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val textView = onView(
            allOf(
                withId(R.id.tvRegion), withText("Russia"),
                childAtPosition(
                    allOf(
                        withId(R.id.btnRegion),
                        childAtPosition(
                            withId(R.id.settingsGeneralContainer),
                            3
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Russia")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
