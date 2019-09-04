package ejarosiewicz.com.apptemplate.requester.test

import android.app.Activity
import androidx.test.rule.ActivityTestRule
import com.mauriciotogneri.greencoffee.GreenCoffeeConfig
import com.mauriciotogneri.greencoffee.GreenCoffeeTest
import com.mauriciotogneri.greencoffee.ScenarioConfig
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import ejarosiewicz.com.apptemplate.requester.test.steps.RequestSteps
import ejarosiewicz.com.apptemplate.requester.view.RequesterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.IOException
import dagger.android.AndroidInjector.Factory
import dagger.android.DispatchingAndroidInjector_Factory
import javax.inject.Provider


@RunWith(Parameterized::class)
class SimpleRequestFeatureTest(scenarioConfig: ScenarioConfig) : GreenCoffeeTest(scenarioConfig) {

    @Rule
    @JvmField
    var activity: ActivityTestRule<RequesterActivity> = ActivityTestRule(RequesterActivity::class.java)


    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        @Throws(IOException::class)
        fun scenarios(): Iterable<ScenarioConfig> {
            return GreenCoffeeConfig()
                .withFeatureFromAssets("assets/simple_request.feature")
                .takeScreenshotOnFail()
                .scenarios()
        }
    }

    @Test
    fun test() {
        start(RequestSteps())
    }
}


inline fun <reified T : Activity> createFakeActivityInjector(crossinline block : T.() -> Unit)
        : DispatchingAndroidInjector<Any> {
    val injector = AndroidInjector<Activity> { instance ->
        if (instance is T) {
            instance.block()
        }
    }
    val factory = Factory<Activity> { injector }
    val map = mapOf(Pair<Class <*>, Provider<Factory<*>>>(T::class.java, Provider { factory }))
    return DispatchingAndroidInjector_Factory.newInstance(map, emptyMap())
}