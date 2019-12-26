package ru.aviasales.ui.activity.Robots
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.travelpayouts.travel.app.R
import ru.aviasales.ui.activity.UtilsForTests.isVisible

class ResultRobot {

    companion object {

        val contentCardBtn = withId(R.id.contentCard)
    }

    fun verifyResultsIsShown(): ResultRobot {
        onView(contentCardBtn).isVisible()
        return this
    }
}