package ru.aviasales.ui.activity.RobotScreens
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.travelpayouts.travel.app.R
import org.hamcrest.Matchers.allOf
import ru.aviasales.ui.activity.UtilsForTests.isGone
import ru.aviasales.ui.activity.UtilsForTests.isInvisible
import ru.aviasales.ui.activity.UtilsForTests.isVisible



class MainRobot {


 companion object {
     val departureBtn = withId(R.id.flDeparture)
     val arrivalBtn = withId(R.id.flArrival)
     val directionText =
         allOf(
             withId(R.id.tvDirectionName),
             isDescendantOfA(
                 withId(R.id.flDeparture)))
     val switchAirportsBtn = withId(R.id.ivSwitch)
     val departureDateBtn = withId(R.id.departureDateView)
     val returnDateBtn = withId(R.id.returnDateView)
     val passangersBtn = withId(R.id.passengersView)
     val searchTicketsBtn = withId(R.id.searchButton)
     val editArrAndDepfield = withId(R.id.editTextMessage)
     val selectedAirportBtn = withId(R.id.rvSelectedAirport)
 }


    fun choseDeparture(): MainRobot {
        onView(departureBtn).isVisible()
        onView(departureBtn).perform(click())
        return this
    }

    fun choseArrival(): MainRobot {
        onView(arrivalBtn).isVisible()
        onView(arrivalBtn).perform(click())
        return this
    }

    fun switchAirports(): MainRobot {
        onView(switchAirportsBtn).isVisible()
        onView(switchAirportsBtn).perform(click())
        return this
    }

    fun choseDeparureDate(): MainRobot {
        onView(departureDateBtn).isVisible()
        onView(departureDateBtn).perform(click())
        return this
    }

    fun choseArrivalDate():MainRobot {
        onView(returnDateBtn).isVisible()
        onView(returnDateBtn).perform(click())
        return this
    }

    fun chosePassanger(): MainRobot {
        onView(passangersBtn).isVisible()
        onView(passangersBtn).perform(click())
        return this
    }

    fun searchticket(): MainRobot {
        onView(searchTicketsBtn).isVisible()
        onView(searchTicketsBtn).perform(click())
        return this
    }

    fun enterDepartureCity(departure:String): MainRobot {
        onView((editArrAndDepfield)).perform(typeTextIntoFocusedView(departure)).check(ViewAssertions.matches(withText(departure)))
        onView((selectedAirportBtn)).isVisible()
        onView(selectedAirportBtn).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView((departureBtn)).isVisible()
        onView((editArrAndDepfield)).isInvisible()
        onView(withText(departure)).check(ViewAssertions.matches(withText(departure)))
        return this
    }

    fun enterArrivalCity(arrival:String): MainRobot {
        onView((editArrAndDepfield)).perform(typeTextIntoFocusedView(arrival)).check(ViewAssertions.matches(withText(arrival)))
        onView((selectedAirportBtn)).isVisible()
        onView(selectedAirportBtn).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView((departureBtn)).isVisible()
        onView((editArrAndDepfield)).isInvisible()
        onView(withText(arrival)).check(ViewAssertions.matches(withText(arrival)))
        return this
    }

}








