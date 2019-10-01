package ejarosiewicz.com.apptemplate.requester.test.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

fun basic(function: BasicRobot.() -> Unit) = BasicRobot().apply { function() }

class BasicRobot {

    fun click(resId: Int): ViewInteraction =
        onView((withId(resId))).perform(ViewActions.click())

    fun matchText(resId: Int, text: String): ViewInteraction =
        onView(resId).matchText(text)

    fun isDisplayed(resId: Int): ViewInteraction =
        onView(resId).isDisplayed()

    private fun onView(resId: Int): ViewInteraction =
        onView((withId(resId)))

    private fun ViewInteraction.matchText(text: String): ViewInteraction =
        check(ViewAssertions.matches(ViewMatchers.withText(text)))

    private fun ViewInteraction.isDisplayed(): ViewInteraction =
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

}