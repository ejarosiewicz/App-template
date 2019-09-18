package ejarosiewicz.com.apptemplate.requester.test.robots

import android.net.wifi.WifiManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import java.util.concurrent.TimeUnit


fun basic(function: BasicRobot.() -> Unit) = BasicRobot().apply { function() }

class BasicRobot {

    fun click(resId: Int) =
        onView((withId(resId))).perform(ViewActions.click())

    fun onView(resId: Int) =
        onView((withId(resId)))

    fun onSnackbar() =
       onView((withId(com.google.android.material.R.id.snackbar_text)))

    fun ViewInteraction.matchText(text: String) =
        check(ViewAssertions.matches(ViewMatchers.withText(text)))

    fun matchText(resId: Int, text: String) =
        onView(resId).matchText(text)

    fun matchSnackbarText(text: String) =
        onSnackbar().matchText(text)
}