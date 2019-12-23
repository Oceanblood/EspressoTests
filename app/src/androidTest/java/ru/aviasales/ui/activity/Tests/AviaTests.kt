package ru.aviasales.ui.activity.Tests
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.screenshot.ScreenCapture
import androidx.test.runner.screenshot.Screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.aviasales.ui.activity.MainActivity
import ru.aviasales.ui.activity.RobotScreens.MainRobot

@RunWith(AndroidJUnit4::class)

class TestExample {

    @get:Rule
       val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
     fun searchTicketTest(){
         MainRobot()
             .choseDeparture()
             .enterDepartureCity("Berlin")
             .choseArrival()
             .enterArrivalCity("Minsk")
             .searchticket()
   }

    @Test
    fun switchAirportsTest(){
        MainRobot()
    }
}
