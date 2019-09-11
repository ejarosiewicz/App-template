package ejarosiewicz.com.apptemplate.requester.test.steps

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps
import com.mauriciotogneri.greencoffee.annotations.Then
import com.mauriciotogneri.greencoffee.annotations.When
import ejarosiewicz.com.apptemplate.requester.test.robots.basic
import ejarosiewicz.com.requester.R

class RequestSteps: GreenCoffeeSteps() {

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
}

