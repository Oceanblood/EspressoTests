package ru.aviasales.ui.activity.Tests

import android.content.Context
import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.aviasales.ui.activity.MainActivity
import ru.aviasales.ui.activity.Robots.MainRobot
import ru.aviasales.ui.activity.Robots.ResultRobot
import ru.aviasales.ui.activity.Robots.RobotSettings
import java.io.File


@RunWith(AndroidJUnit4::class)

class Smoke {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext
        val prefsNames = File(ctx.filesDir.parentFile, "shared_prefs").list() ?: return
        for (fileName in prefsNames) {
            ctx.getSharedPreferences(fileName.replace(".xml", ""), Context.MODE_PRIVATE)
                .edit().clear().commit()
        }

        activityRule.launchActivity(Intent())
    }

// иногда тест падает, хотя при дебаге все ок. ???
    @Test
    fun searchTicket() {
        MainRobot()
            .choseDepartureCity()
            .enterDepartureCity("Berlin")
            .choseArrivalCity()
            .enterArrivalCity("Minsk")
            .tapOnSearchBtn()
        ResultRobot()
            .verifyResultsIsShown()
    }

    @Test
    fun switchAirports() {
        MainRobot()
            .choseDepartureCity()
            .enterDepartureCity("Berlin")
            .choseArrivalCity()
            .enterArrivalCity("Minsk")
            .switchAirports()
            .checkAirportsChanges("Minsk", "Berlin")
    }

    @Test
    fun addPassenger() {
        MainRobot()
            .openPassengers()
            .addAdult()
            .closePassengers()
            .verifyClassAndNumberOfPassenger("2 passengers, economy")
    }

    @Test
    fun removePassenger() {
        MainRobot()
            .openPassengers()
            .addAdult()
            .closePassengers()
            .verifyClassAndNumberOfPassenger("2 passengers, economy")
            .removeAdult()
            .closePassengers()
            .verifyClassAndNumberOfPassenger("1 passenger, economy")

    }

    @Test
    fun choseBusinessClass() {
        MainRobot()
            .openPassengers()
            .choseBusinessClass()
            .closePassengers()
            .verifyClassAndNumberOfPassenger("1 passenger, business")
    }

    @Test
    fun switchBusinessOnEconomyClass() {
        MainRobot()
            .openPassengers()
            .choseBusinessClass()
            .closePassengers()
            .verifyClassAndNumberOfPassenger("1 passenger, business")
            .choseEconomyClass()
            .closePassengers()
            .verifyClassAndNumberOfPassenger("1 passenger, economy")
    }

    @Test
    fun userDontNeedReturnTicket() {
        MainRobot()
            .choseArrivalDate()
            .tapOnDontNeedTicketBtn()
            .verifyArrivalDateText("+ Add return flight")

    }

    @Test
    fun choseDepartureDate() {
        MainRobot()
            .choseDepartureDate()
            .choseDepartureDay()
            .verifyDepartureDate("3 January, Fri")
    }

    @Test
    fun choseArrivalDate() {
        MainRobot()
            .choseArrivalDate()
            .choseArrivalDay()
            .verifyArrivalDate("8 January, Wed")
    }

// Не смог понять, почему на шаге .typeCurrency апа банально не отдает список, по этой причине тест валится
    @Test
    fun changeCurrency() {
        RobotSettings()
            .tapOnInfoTab()
            .tapOnSettings()
            .tapOnCurrency()
            .typeCurrency("Euro")
            .checkCurrencyIndex("Euro")
    }
// need to fix this
    @Test
    fun changeRegion(){
        RobotSettings()
            .tapOnInfoTab()
            .tapOnSettings()
            .tapOnRegion()
            .choseRegion("Russia")
    }


}

