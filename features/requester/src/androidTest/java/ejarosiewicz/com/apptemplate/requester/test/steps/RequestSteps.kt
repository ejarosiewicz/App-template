package ejarosiewicz.com.apptemplate.requester.test.steps

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.mauriciotogneri.greencoffee.GreenCoffeeSteps
import com.mauriciotogneri.greencoffee.annotations.Then
import com.mauriciotogneri.greencoffee.annotations.When
import ejarosiewicz.com.requester.R


class RequestSteps: GreenCoffeeSteps() {

    @When("^I want to make request$")
    fun makeSomeRequest() {
        onViewWithId(R.id.textView).click()
    }

    @Then("^I see the result on the screen$")
    fun verifyResults() {
        onViewWithId(R.id.textView).check(matches(withText("chuj")))
    }


}