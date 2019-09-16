package ejarosiewicz.com.apptemplate.requester.test.robots

import android.content.Context
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry

fun basic(function: BasicRobot.() -> Unit) = BasicRobot().apply { function() }

class BasicRobot {

    fun click(resId: Int) =
        onView((withId(resId))).perform(ViewActions.click())

    fun onView(resId: Int) =
        onView((withId(resId)))

    fun ViewInteraction.matchText(text: String) =
        check(ViewAssertions.matches(ViewMatchers.withText(text)))

    fun matchText(resId: Int, text: String) =
        onView(resId).matchText(text)


    fun changeNetworkState(enabled: Boolean){
        val instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        changeWifiNetworkState(instrumentationContext, enabled)
        changeMobileNetworkState(instrumentationContext, enabled)
    }

    private fun changeMobileNetworkState(context: Context, enabled: Boolean){
        val telephonyService = ContextCompat.getSystemService(context, TelephonyManager::class.java)
        val setMobileDataEnabledMethod =
            telephonyService?.javaClass?.getDeclaredMethod("setDataEnabled", Boolean::class.javaPrimitiveType)

        setMobileDataEnabledMethod?.invoke(telephonyService, enabled)
    }

    private fun changeWifiNetworkState(context: Context, enabled: Boolean){
        val wifi = ContextCompat.getSystemService(context, WifiManager::class.java)
        wifi?.isWifiEnabled = enabled
    }
}