package ru.aviasales.ui.activity.testRobots

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.travelpayouts.travel.app.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import ru.aviasales.ui.activity.UtilsForTests.isVisible


fun robotSettings(f: RobotSettings.() -> Unit) = RobotSettings().apply(f)

class RobotSettings {


    companion object {
        val infoTab: Matcher<View> = withId(R.id.bb_appinfo_tab)
        val settingBtn: Matcher<View> = withId(R.id.btnSettings)
        val currencyBtn: Matcher<View> = withId(R.id.btnCurrency)
        val currencyText: Matcher<View> =
            Matchers.allOf(
                withId(R.id.tvCurrencyCode),
                ViewMatchers.isDescendantOfA(withId(R.id.btnCurrency))
            )
        val editCurrency: Matcher<View> = withId(R.id.editTextMessage)
        val currencyName: Matcher<View> = withId(R.id.tvCurrencyName)
        val regionBtn: Matcher<View> = withId(R.id.btnRegion)
        val region: Matcher<View> = withId(R.id.tvRegion)
        val regionName: Matcher<View> =
            Matchers.allOf(
                withId(R.id.tvRegionName),
                ViewMatchers.isDescendantOfA(withId(R.id.rvItemsRecycler))
            )

    }

    fun tapOnRegion() {
        onView(regionBtn).isVisible()
        onView(regionBtn).perform(click())
    }

    fun enterRegion(text: String) {
        onView(regionName).isVisible()
        onView(editCurrency).perform(ViewActions.typeTextIntoFocusedView(text)).check(
            ViewAssertions.matches(
                withText(text)
            )
        )
        closeSoftKeyboard()
    }

    fun verifyRegion(Region: String) {
        onView(regionBtn).isVisible()
        onView(region).check(ViewAssertions.matches(withText(Region)))
    }

    fun choseRegion(region: String) {
        onView(regionName).check(ViewAssertions.matches(withText(region)))
        onView(regionName).perform(click())
    }

    fun typeCurrency(currency: String) {
        onView(editCurrency).isVisible()
        onView(editCurrency).perform(ViewActions.typeTextIntoFocusedView(currency))
            .check(ViewAssertions.matches(withText(currency)))
        closeSoftKeyboard()
        onView(currencyName).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
    }


    fun tapOnInfoTab() {
        onView(infoTab).isVisible()
        onView(infoTab).perform(click())
    }

    fun tapOnSettings() {
        onView(settingBtn).isVisible()
        onView(settingBtn).perform(click())
    }

    fun tapOnCurrency() {
        onView(currencyBtn).isVisible()
        onView(currencyBtn).perform(click())
    }

    fun checkCurrencyIndex(id: String) {
        onView(currencyText).check(ViewAssertions.matches(withText(id)))
    }

}