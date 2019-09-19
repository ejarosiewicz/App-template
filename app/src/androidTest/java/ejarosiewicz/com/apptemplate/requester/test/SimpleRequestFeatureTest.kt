package ejarosiewicz.com.apptemplate.requester.test

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.rule.ActivityTestRule
import com.mauriciotogneri.greencoffee.GreenCoffeeConfig
import com.mauriciotogneri.greencoffee.GreenCoffeeTest
import com.mauriciotogneri.greencoffee.ScenarioConfig
import ejarosiewicz.com.apptemplate.requester.test.assets.MockResponseReader
import ejarosiewicz.com.apptemplate.requester.test.steps.RequestSteps
import ejarosiewicz.com.apptemplate.requester.view.RequesterActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
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

    private val mockWebServer = MockWebServer()
    private lateinit var idlingResource: IdlingResource
    private lateinit var mockResponseContent: String


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

    @Before
    fun setUp() {
        val activityScenario = ActivityScenario.launch(RequesterActivity::class.java)
        activityScenario.onActivity { activity ->
            idlingResource = activity.idlingResource
            IdlingRegistry.getInstance().register(idlingResource)
            mockResponseContent = MockResponseReader.read("some_response.json")
        }
        mockWebServer.start(8000)
    }

    @Test
    fun test() {
        start(RequestSteps(mockWebServer, mockResponseContent))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
        mockWebServer.shutdown()
    }
}