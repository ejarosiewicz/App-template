package ejarosiewicz.com.apptemplate.requester.test

import androidx.test.rule.ActivityTestRule
import com.mauriciotogneri.greencoffee.GreenCoffeeConfig
import com.mauriciotogneri.greencoffee.GreenCoffeeTest
import com.mauriciotogneri.greencoffee.ScenarioConfig
import ejarosiewicz.com.apptemplate.requester.test.steps.RequestSteps
import ejarosiewicz.com.apptemplate.requester.view.RequesterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.IOException


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