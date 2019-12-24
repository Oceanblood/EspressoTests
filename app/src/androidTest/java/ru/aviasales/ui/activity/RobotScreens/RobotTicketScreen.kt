package ru.aviasales.ui.activity.RobotScreens

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.travelpayouts.travel.app.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import ru.aviasales.ui.activity.UtilsForTests.isInvisible
import ru.aviasales.ui.activity.UtilsForTests.isVisible


class MainRobot {


    companion object {

        // Main Screen Btn & Views

        val departureBtn = withId(R.id.flDeparture)
        val arrivalBtn = withId(R.id.flArrival)
        val directionText =
            allOf(withId(R.id.tvDirectionName), isDescendantOfA(withId(R.id.flDeparture)))
        val arrivalText =
            allOf(withId(R.id.tvDirectionName), isDescendantOfA(withId(R.id.flArrival)))
        val switchAirportsBtn = withId(R.id.ivSwitch)
        val departureDateBtn = withId(R.id.departureDateView)
        val returnDateBtn = withId(R.id.returnDateView)
        val searchTicketsBtn = withId(R.id.searchButton)
        val editArrAndDepfield = withId(R.id.editTextMessage)
        val selectedAirportBtn = withId(R.id.rvSelectedAirport)
        val dateNumberBtn = withId(R.id.calendar_grid)
        val passangersBtn = withId(R.id.passengersView)

        // Bottom sheet Btns & Views

        val addPassangerBtn = (Matchers.allOf(
            ViewMatchers.withId(R.id.btnIncrement),
            ViewMatchers.isDescendantOfA(ViewMatchers.withId(R.id.countAdults))
        ))
        val removePassangerBtn = (Matchers.allOf(
            ViewMatchers.withId(R.id.btnDecrement),
            ViewMatchers.isDescendantOfA(ViewMatchers.withId(R.id.countAdults))
        ))
        val countOfPassangers =
            allOf(withId(R.id.passengersTitle), isDescendantOfA(withId(R.id.passengersView)))
        val closePassengersBtn = withId(R.id.btnClosePassengers)
        val numberOfPassangersView = withId(R.id.passengersTitle)
        val economyClassBtn = withId(R.id.rbEconomy)
        val businessBtn = withId(R.id.rbBusiness)


    }

    // Main Functions

    fun verifyNumberOfPassangers(number: String): MainRobot {
        onView(withId(R.id.passengersTitle)).check(ViewAssertions.matches(withText(number)))
        return this
    }

    fun addFlightDate(): MainRobot {
        onView(departureDateBtn).perform(click())
        onView(dateNumberBtn).isVisible()
        onView(dateNumberBtn).perform(click())
        //onData(dateNumberBtn).inAdapterView(addPassangerBtn).atPosition(0).perform(click())
        return this
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

    fun choseArrivalDate(): MainRobot {
        onView(returnDateBtn).isVisible()
        onView(returnDateBtn).perform(click())
        return this
    }

    fun tapOnSearchBtn(): MainRobot {
        onView(searchTicketsBtn).isVisible()
        onView(searchTicketsBtn).perform(click())
        return this
    }

    fun enterDepartureCity(departure: String): MainRobot {
        onView((editArrAndDepfield)).perform(typeTextIntoFocusedView(departure))
            .check(ViewAssertions.matches(withText(departure)))
        onView((selectedAirportBtn)).isVisible()
        onView(selectedAirportBtn).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView((departureBtn)).isVisible()
        onView((editArrAndDepfield)).isInvisible()
        onView(directionText).check(ViewAssertions.matches(withText(departure)))
        return this
    }

    fun enterArrivalCity(arrival: String): MainRobot {
        onView((editArrAndDepfield)).perform(typeTextIntoFocusedView(arrival))
            .check(ViewAssertions.matches(withText(arrival)))
        onView((selectedAirportBtn)).isVisible()
        onView(selectedAirportBtn).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView((departureBtn)).isVisible()
        onView((editArrAndDepfield)).isInvisible()
        onView(arrivalText).check(ViewAssertions.matches(withText(arrival)))
        return this
    }


    // Bottom sheet Functions

    fun chosePassanger(): MainRobot {
        onView(passangersBtn).isVisible()
        onView(passangersBtn).perform(click())
        return this
    }

    fun addAdult(): MainRobot {
        chosePassanger()
        onView(addPassangerBtn).isVisible()
        onView(addPassangerBtn).perform(click())
        return this
    }

    fun removeAdult(): MainRobot {
        chosePassanger()
        onView(removePassangerBtn).isVisible()
        onView(removePassangerBtn).perform(click())
        return this
    }

}










