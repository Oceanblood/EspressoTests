package ru.aviasales.ui.activity.testRobots

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.travelpayouts.travel.app.R
import org.hamcrest.Matcher
import ru.aviasales.ui.activity.UtilsForTests.isVisible

fun resultRobot(f: ResultRobot.() -> Unit) = ResultRobot().apply(f)

class ResultRobot {

    companion object {

        val contentCardBtn: Matcher<View> = withId(R.id.contentCard)
    }

    fun verifyResultsIsShown() {
        onView(contentCardBtn).isVisible()
    }
}
