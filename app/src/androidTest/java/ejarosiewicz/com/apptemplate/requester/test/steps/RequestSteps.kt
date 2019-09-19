package ejarosiewicz.com.apptemplate.requester.test.steps

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps
import com.mauriciotogneri.greencoffee.annotations.Given
import com.mauriciotogneri.greencoffee.annotations.Then
import com.mauriciotogneri.greencoffee.annotations.When
import ejarosiewicz.com.apptemplate.R
import ejarosiewicz.com.apptemplate.requester.test.robots.basic
import ejarosiewicz.com.apptemplate.requester.test.robots.network
import okhttp3.mockwebserver.MockWebServer


class RequestSteps(private val mockWebServer: MockWebServer,
                   private val mockResponseContent: String) : GreenCoffeeSteps() {

    @Given("^Network is enabled$")
    fun enableNetwork() {
        network {
            changeNetworkState(true)
            makeResponseSuccess(mockWebServer, mockResponseContent)
        }
    }

    @Given("^Network is disabled$")
    fun disableNetwork() {
        network {
            changeNetworkState(false)
        }
    }

    @Given("^Network has errors$")
    fun prepareResponseFail() {
        network {
            changeNetworkState(true)
            makeResponseFail(mockWebServer)
        }
    }

    @When("^I want to make request$")
    fun makeSomeRequest() {
        basic {
            click(R.id.textView)
        }
    }

    @Then("^I see the result on the screen$")
    fun verifyResults() {
        basic {
            matchText(R.id.textView, "world")
        }
    }

    @Then("^I see no network message on the screen$")
    fun verifyNoNetworkMessage() {
        basic {
            matchText(R.id.textView,"No network connection")
        }
    }

    @Then("^I see network error message on the screen$")
    fun verifyNetworErrorkMessage() {
        basic {
            matchText(R.id.textView,"Network error")
        }
    }
}

