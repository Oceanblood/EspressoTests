package ru.aviasales.ui.activity.tests

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
import ru.aviasales.ui.activity.testRobots.mainRobot
import ru.aviasales.ui.activity.testRobots.resultRobot
import ru.aviasales.ui.activity.testRobots.robotSettings
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

    // иногда тест падает, хотя при дебаге все ок. не смог понять почему
    @Test
    fun searchTicket() {
        mainRobot {
            choseDepartureCity()
            enterDepartureCity("Berlin")
            choseArrivalCity()
            enterArrivalCity("Minsk")
            tapOnSearchBtn()
        }
        resultRobot {
            verifyResultsIsShown()
        }
    }

    @Test
    fun switchAirports() {
        mainRobot {
            choseDepartureCity()
            enterDepartureCity("Berlin")
            choseArrivalCity()
            enterArrivalCity("Minsk")
            switchAirports()
            checkAirportsChanges("Minsk", "Berlin")
        }
    }

    @Test
    fun addPassenger() {
        mainRobot {
            openPassengers()
            addAdult()
            closePassengers()
            verifyClassAndNumberOfPassenger("2 passengers, economy")
        }
    }

    @Test
    fun removePassenger() {
        mainRobot {
            openPassengers()
            addAdult()
            closePassengers()
            verifyClassAndNumberOfPassenger("2 passengers, economy")
            removeAdult()
            closePassengers()
            verifyClassAndNumberOfPassenger("1 passenger, economy")
        }
    }

    @Test
    fun choseBusinessClass() {
        mainRobot {
            openPassengers()
            choseBusinessClass()
            closePassengers()
            verifyClassAndNumberOfPassenger("1 passenger, business")
        }
    }

    @Test
    fun switchBusinessOnEconomyClass() {
        mainRobot {
            openPassengers()
            choseBusinessClass()
            closePassengers()
            verifyClassAndNumberOfPassenger("1 passenger, business")
            choseEconomyClass()
            closePassengers()
            verifyClassAndNumberOfPassenger("1 passenger, economy")
        }
    }

    @Test
    fun userDontNeedReturnTicket() {
        mainRobot {
            choseArrivalDate()
            tapOnDontNeedTicketBtn()
            verifyArrivalDateText("+ Add return flight")
        }
    }

    @Test
    fun choseDepartureDate() {
        mainRobot {
            choseDepartureDate()
            choseDepartureDay()
            verifyDepartureDate("13 January, Mon")
        }
    }

    @Test
    fun choseArrivalDate() {
        mainRobot {
            choseArrivalDate()
            choseArrivalDay()
            verifyArrivalDate("28 January, Tue")
        }
    }

    // Не смог понять, почему на шаге .typeCurrency апа банально не отдает список, по этой причине тест валится
    @Test
    fun changeCurrency() {
        robotSettings {
            tapOnInfoTab()
            tapOnSettings()
            tapOnCurrency()
            typeCurrency("Euro")
            checkCurrencyIndex("Euro")
        }
    }

    @Test
    fun changeRegion() {
        robotSettings {
            tapOnInfoTab()
            tapOnSettings()
            tapOnRegion()
            enterRegion("Russia")
            choseRegion("Russia")
            verifyRegion("Russia")
        }
    }

}

