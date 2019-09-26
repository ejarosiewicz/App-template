package ejarosiewicz.com.apptemplate.requester.test.steps

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps
import com.mauriciotogneri.greencoffee.annotations.Given
import com.mauriciotogneri.greencoffee.annotations.Then
import com.mauriciotogneri.greencoffee.annotations.When
import ejarosiewicz.com.apptemplate.R
import ejarosiewicz.com.apptemplate.requester.test.robots.basic
import ejarosiewicz.com.apptemplate.requester.test.robots.network


class RequestSteps : GreenCoffeeSteps() {

    @Given("^Network is enabled$")
    fun enableNetwork() {
        network {
            changeNetworkState(true)
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
        }
    }

    @When("^I want to make request$")
    fun makeSomeRequest() {
        basic {
            click(R.id.textView)
        }
    }

    @Then("^I see header on the screen$")
    fun verifyHeader() {
        basic {
            isDisplayed(R.id.header)
        }
    }

    @Then("^I see content on the screen$")
    fun verifyContent() {
        basic {
            isDisplayed(R.id.content)
        }
    }

    @Then("^I see text only container on the screen$")
    fun verifyTextOnly() {
        basic {
            isDisplayed(R.id.textcontainer)
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

