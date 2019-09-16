package ejarosiewicz.com.apptemplate.requester.test.steps


import com.mauriciotogneri.greencoffee.GreenCoffeeSteps
import com.mauriciotogneri.greencoffee.annotations.Given
import com.mauriciotogneri.greencoffee.annotations.Then
import com.mauriciotogneri.greencoffee.annotations.When
import ejarosiewicz.com.apptemplate.R
import ejarosiewicz.com.apptemplate.requester.test.robots.basic


class RequestSteps: GreenCoffeeSteps() {

    @Given("Network is enabled$")
    fun enableNetwork() {
        basic {
            changeNetworkState(true)
        }
    }

    @Given("Network is disabled$")
    fun disableNetwork() {
        basic {
            changeNetworkState(false)
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

    @Then("^I see no error message on the screen$")
    fun verifyNoNetworkMessage() {
        basic {
            matchSnackbarText("No network connection")
        }
    }
}

