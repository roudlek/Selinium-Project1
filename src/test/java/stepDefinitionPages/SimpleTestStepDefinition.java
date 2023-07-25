package stepDefinitionPages;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SimpleTestStepDefinition {
    @Given("User enters coffee shop")
    public void user_enters_coffee_shop() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("hello");
    }
    @When("User asks for coffee")
    public void user_asks_for_coffee() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("hello");
    }
    @Then("We gave him coffe")
    public void we_gave_him_coffe() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("hello");
    }
}
