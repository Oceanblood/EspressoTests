package ru.aviasales.ui.activity.Robots

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
import org.hamcrest.Matchers
import ru.aviasales.ui.activity.UtilsForTests.isVisible


class RobotSettings {


    companion object {
        val infoTab = withId(R.id.bb_appinfo_tab)
        val settingBtn = withId(R.id.btnSettings)
        val currencyBtn = withId(R.id.btnCurrency)
        val currencyText =
            Matchers.allOf(
                withId(R.id.tvCurrencyCode),
                ViewMatchers.isDescendantOfA(withId(R.id.btnCurrency))
            )
        val editCurrency = withId(R.id.editTextMessage)
        val currencyName = withId(R.id.tvCurrencyName)
        val regionBtn = withId(R.id.btnRegion)
        val regionName1 = withId(R.id.tvRegionName)
        val regionName =
            Matchers.allOf(
                withId(R.id.tvDirectionName),
                ViewMatchers.isDescendantOfA(withId(R.id.rvItemsRecycler))
            )
    }

    fun tapOnRegion(): RobotSettings {
        onView(regionBtn).isVisible()
        onView(regionBtn).perform(click())
        return this
    }

    fun choseRegion(text:String): RobotSettings {
        onView(regionName).isVisible()
        onView(editCurrency).perform(ViewActions.typeTextIntoFocusedView(text)).check(ViewAssertions.matches(
            withText(text)))
        onView(regionName).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()))
        return this
    }

    fun typeCurrency(currency: String): RobotSettings {
        onView(editCurrency).isVisible()
        onView(editCurrency).perform(ViewActions.typeTextIntoFocusedView(currency))
            .check(ViewAssertions.matches(ViewMatchers.withText(currency)))
        closeSoftKeyboard()
        onView(currencyName).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()))
        return this
    }


    fun tapOnInfoTab(): RobotSettings {
        onView(infoTab).isVisible()
        onView(infoTab).perform(click())
        return this
    }

    fun tapOnSettings(): RobotSettings {
        onView(settingBtn).isVisible()
        onView(settingBtn).perform(click())
        return this
    }

    fun tapOnCurrency(): RobotSettings {
        onView(currencyBtn).isVisible()
        onView(currencyBtn).perform(click())
        return this
    }

    fun checkCurrencyIndex(id: String): RobotSettings {
        onView(currencyText).check(ViewAssertions.matches(ViewMatchers.withText(id)))
        return this
    }

}