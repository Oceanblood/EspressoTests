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
import ru.aviasales.ui.activity.RobotScreens.MainRobot
import java.io.File


@RunWith(AndroidJUnit4::class)

class TestExample {

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


    @Test
    fun searchTicket() {
        MainRobot()
            .choseDeparture()
            .enterDepartureCity("Berlin")
            .choseArrival()
            .enterArrivalCity("Minsk")
            .tapOnSearchBtn()
    }

    @Test
    fun departure() {
        MainRobot()
            .addFlightDate()

    }

    @Test
    fun switchAirports() {
        MainRobot()
    }

    @Test
    fun addPassenger() {
       MainRobot()
            .chosePassanger()
            .addAdult()
        MainRobot()
            .verifyNumberOfPassangers("2 passengers, economy")
    }

    @Test
    fun removePassenger() {
        MainRobot()
            .chosePassanger()
            .addAdult()
            .verifyNumberOfPassangers("2 passengers, economy")
            .removeAdult()
            .verifyNumberOfPassangers("1 passenger, economy")

    }

    @Test
    fun choseBusinessClass() {
        MainRobot()
    }

    @Test
    fun addFlight() {
        MainRobot()
            .addFlightDate()
    }
}
