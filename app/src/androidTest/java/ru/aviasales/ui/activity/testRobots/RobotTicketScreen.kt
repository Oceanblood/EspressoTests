package ru.aviasales.ui.activity.testRobots

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.travelpayouts.travel.app.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.aviasales.ui.activity.UtilsForTests.isInvisible
import ru.aviasales.ui.activity.UtilsForTests.isVisible


fun mainRobot(f: MainRobot.() -> Unit) = MainRobot().apply(f)

class MainRobot {


    companion object {

        // Main Screen Btn & Views

        val departureBtn: Matcher<View> = withId(R.id.flDeparture)
        val arrivalBtn: Matcher<View> = withId(R.id.flArrival)
        val directionText: Matcher<View> =
            allOf(withId(R.id.tvDirectionName), isDescendantOfA(withId(R.id.flDeparture)))
        val arrivalText: Matcher<View> =
            allOf(withId(R.id.tvDirectionName), isDescendantOfA(withId(R.id.flArrival)))
        val switchAirportsBtn: Matcher<View> = withId(R.id.ivSwitch)
        val searchTicketsBtn: Matcher<View> = withId(R.id.searchButton)
        val editArrAndDeparchfield: Matcher<View> = withId(R.id.editTextMessage)
        val selectedAirportBtn: Matcher<View> = withId(R.id.rvSelectedAirport)
        val passengersBtn: Matcher<View> = withId(R.id.passengersView)

        // Bottom sheet Btns & Views

        val addPassengerBtn: Matcher<View> = (allOf(
            withId(R.id.btnIncrement),
            isDescendantOfA(withId(R.id.countAdults))
        ))
        val removePassangerBtn: Matcher<View> = (allOf(
            withId(R.id.btnDecrement),
            isDescendantOfA(withId(R.id.countAdults))
        ))
        val countOfPassangers: Matcher<View> =
            allOf(withId(R.id.passengersTitle), isDescendantOfA(withId(R.id.passengersView)))
        val closePassengersBtn: Matcher<View> = withId(R.id.btnClosePassengers)
        val numberOfPassangersView: Matcher<View> = withId(R.id.passengersTitle)
        val economyClassBtn: Matcher<View> = withId(R.id.rbEconomy)
        val businessBtn: Matcher<View> = withId(R.id.rbBusiness)

        //Calendar

        val departureDateBtn: Matcher<View> = withId(R.id.departureDateView)
        val returnDateBtn: Matcher<View> = withId(R.id.returnDateView)
        val iDointNeedticketBtn: Matcher<View> = withId(R.id.btnAdditionalFirst)
        val departureDateText: Matcher<View> =
            allOf(withId(R.id.tvDate), isDescendantOfA(withId(R.id.departureDateView)))
        val arrivalDateText: Matcher<View> =
            allOf(withId(R.id.tvDate), isDescendantOfA(withId(R.id.returnDateView)))
        val dateNumberBtn: Matcher<View> = withId(R.id.calendar_grid)
        val calendarDepartureDate: Matcher<View> = allOf(
            withText("13"),
            withParent(
                withParent(
                    allOf(
                        withId(R.id.calendar_grid),
                        withParent(withParentIndex(0))
                    )
                )
            )
        )
        val calendarArrivalDate: Matcher<View> = allOf(
            withText("28"),
            withParent(
                withParent(
                    allOf(
                        withId(R.id.calendar_grid),
                        withParent(withParentIndex(0))
                    )
                )
            )
        )

    }

    // Main Functions

    fun verifyArrivalDate(arrivalDate: String) {
        onView(arrivalDateText).isVisible()
        onView(arrivalDateText).check(ViewAssertions.matches(withText(arrivalDate)))
    }

    fun verifyDepartureDate(departureDate: String) {
        onView(departureDateText).isVisible()
        onView(departureDateText).check(ViewAssertions.matches(withText(departureDate)))
    }

    fun choseDepartureCity() {
        onView(departureBtn).isVisible()
        onView(departureBtn).perform(click())
    }

    fun choseArrivalCity() {
        onView(arrivalBtn).isVisible()
        onView(arrivalBtn).perform(click())
    }

    fun switchAirports() {
        onView(switchAirportsBtn).isVisible()
        onView(switchAirportsBtn).perform(click())
    }

    fun checkAirportsChanges(departure: String, arrival: String) {
        onView(directionText).check(ViewAssertions.matches(withText(departure)))
        onView(arrivalText).check(ViewAssertions.matches(withText(arrival)))
    }

    fun choseDepartureDate() {
        onView(departureDateBtn).isVisible()
        onView(departureDateBtn).perform(click())
    }

    fun choseArrivalDate() {
        onView(returnDateBtn).isVisible()
        onView(returnDateBtn).perform(click())
    }

    fun tapOnSearchBtn() {
        onView(searchTicketsBtn).isVisible()
        onView(searchTicketsBtn).perform(click())
    }

    fun enterDepartureCity(departure: String) {
        onView((editArrAndDeparchfield)).perform(typeTextIntoFocusedView(departure))
            .check(ViewAssertions.matches(withText(departure)))
        onView((selectedAirportBtn)).isVisible()
        onView(selectedAirportBtn).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView((departureBtn)).isVisible()
        onView((editArrAndDeparchfield)).isInvisible()
        onView(directionText).check(ViewAssertions.matches(withText(departure)))
    }

    fun enterArrivalCity(arrival: String) {
        onView((editArrAndDeparchfield)).perform(typeTextIntoFocusedView(arrival))
            .check(ViewAssertions.matches(withText(arrival)))
        onView((selectedAirportBtn)).isVisible()
        onView(selectedAirportBtn).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView((departureBtn)).isVisible()
        onView((editArrAndDeparchfield)).isInvisible()
        onView(arrivalText).check(ViewAssertions.matches(withText(arrival)))
    }


    // Bottom sheet Functions

    fun verifyClassAndNumberOfPassenger(number: String) {
        onView(withId(R.id.passengersTitle)).check(ViewAssertions.matches(withText(number)))
    }


    fun openPassengers() {
        onView(passengersBtn).isVisible()
        onView(passengersBtn).perform(click())
    }

    fun addAdult() {
        openPassengers()
        onView(addPassengerBtn).isVisible()
        onView(addPassengerBtn).perform(click())
    }

    fun removeAdult() {
        openPassengers()
        onView(removePassangerBtn).isVisible()
        onView(removePassangerBtn).perform(click())
    }

    fun choseEconomyClass() {
        openPassengers()
        onView(economyClassBtn).isVisible()
        onView(economyClassBtn).perform(click())

    }

    fun choseBusinessClass() {
        openPassengers()
        onView(businessBtn).isVisible()
        onView(businessBtn).perform(click())
    }

    fun verifyEconomySelected() {
        openPassengers()
        onView(economyClassBtn).isVisible()
        // onView(economyClassBtn).
    }

    fun closePassengers() {
        onView(closePassengersBtn).isVisible()
        onView(closePassengersBtn).perform(click())
    }

    // Calendar Func

    fun tapOnDontNeedTicketBtn() {
        onView(iDointNeedticketBtn).isVisible()
        onView(iDointNeedticketBtn).perform(click())
    }

    fun verifyArrivalDateText(text: String) {
        onView(arrivalDateText).check(ViewAssertions.matches(withText(text)))
    }

    fun choseDepartureDay() {
        onView(dateNumberBtn).isVisible()
        onView(calendarDepartureDate).isVisible()
        onView(calendarDepartureDate).perform(click())
    }

    fun choseArrivalDay() {
        onView(dateNumberBtn).isVisible()
        onView(calendarArrivalDate).isVisible()
        onView(calendarArrivalDate).perform(click())
    }

}











